package ru.mirea.NettyClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import ru.mirea.EncoderDecoder.Decoder;
import ru.mirea.task.Task;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        //ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        Task task = Decoder.decodedTaskServer(ByteBufUtil.getBytes(in));
        System.out.println("Client received: " + task.print());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
