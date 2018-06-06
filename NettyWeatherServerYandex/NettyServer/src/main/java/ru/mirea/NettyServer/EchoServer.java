package ru.mirea.NettyServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;
import ru.mirea.task.Task;
import ru.mirea.task.TaskExecutor;

public class EchoServer {

    private final int port;
    public Queue<Task> inQueue = new ArrayDeque<>();
    public Queue<Task> outQueue = new ArrayDeque<>();

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        
        new EchoServer(8000).start();
    }

    public void start() throws Exception {
        
        TaskExecutor execut = new TaskExecutor(inQueue, outQueue);
  
        Thread te0 = new Thread(execut);
        Thread te1 = new Thread(execut);
        Thread te2 = new Thread(execut);
        Thread te3 = new Thread(execut);
        Thread te4 = new Thread(execut);

        te0.start();
        te1.start();
        te2.start();
        te3.start();
        te4.start();


        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler(inQueue, outQueue));
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
