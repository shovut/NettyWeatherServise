/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.NettyClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
/**
 *
 * @author msi 111
 */
@Sharable 
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public void channelActive(ChannelHandlerContext ctx) {
    System.out.println("Connected");
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty MAY rock!", CharsetUtil.UTF_8));
}

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}