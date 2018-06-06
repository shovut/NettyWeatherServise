package ru.mirea.NettyServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.Queue;
import ru.mirea.EncoderDecoder.Decoder;
import ru.mirea.EncoderDecoder.Encoder;
import ru.mirea.task.Task;
import ru.mirea.task.TaskExecutor;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private Queue<Task> inQueue;
    private Queue<Task> outQueue;
    
    EchoServerHandler(Queue<Task> inQueue, Queue<Task> outQueue) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        byte[] b = (ByteBufUtil.getBytes(in));
        Task task = Decoder.decodedTaskClient(ByteBufUtil.getBytes(in));
        System.out.println("Server received: \n" + task.print());
        
        /*Здесь вход в поток*/
        inQueue.add(task);
        while(outQueue.isEmpty()){
        Thread.sleep(100);
        }
        task = outQueue.poll();
        /***************/
        System.out.println(task.getWeather());
        b = Encoder.encodedTaskServer(task);
        ctx.writeAndFlush(Unpooled.copiedBuffer(b));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
