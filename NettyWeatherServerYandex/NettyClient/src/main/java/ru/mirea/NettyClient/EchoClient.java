package ru.mirea.NettyClient;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.util.Scanner;
import ru.mirea.EncoderDecoder.Encoder;
import ru.mirea.task.Task;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            
            ChannelHandlerContext ctx = f.channel().pipeline().firstContext();
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            String[] tmp = new String[2];
            Task task; byte[] temp;
            while (!str.equals("exit")) {
                try{
                tmp = str.split(";");
                if(tmp.length != 2){
                    str = scan.nextLine();
                    continue;
                }
                task = new Task(tmp[0], tmp[1]);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Exeption");
                    str = scan.nextLine();
                    continue;
                }
                temp = Encoder.encodedTaskClient(task);
                ctx.writeAndFlush(Unpooled.copiedBuffer(Encoder.encodedTaskClient(task)));
                str = scan.nextLine();
            }

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        

        new EchoClient("localhost", 8000).start();
    }
}
