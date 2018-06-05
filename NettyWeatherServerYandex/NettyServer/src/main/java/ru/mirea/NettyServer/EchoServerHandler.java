package ru.mirea.NettyServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import ru.mirea.EncoderDecoder.Decoder;
import ru.mirea.EncoderDecoder.Encoder;
import ru.mirea.task.Task;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        

        byte[] b = (ByteBufUtil.getBytes(in));
        Task task = Decoder.decodedTaskClient(ByteBufUtil.getBytes(in));
        System.out.println("Server received: " + task.print());
        task.setWeather("+10");
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
