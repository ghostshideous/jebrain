package com.test.chatapp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static DefaultChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel ch = ctx.channel();
        channels.writeAndFlush("[Server=====]" + ch.remoteAddress() + "加入 \n");
        channels.add(ch);
    }

    public void handlerRemove(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[Server=====]" + channel.remoteAddress() + "离开\n");
    }


    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        for (Channel ch : channels) {
            if (ch != channel) {
                ch.writeAndFlush("[" + channel.remoteAddress() + "]" + s + "\n");
            } else {
                ch.writeAndFlush("[you]:" + s + "\n");
            }
        }
    }

    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println("SimpleChannelChatServer:"+channel.remoteAddress() + "在线");
    }

    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println("SimpleChannelChantServer:" + channel.remoteAddress() + "掉线");

    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel channel = ctx.channel();
        System.out.println("SimpleChannelChantServer:" + channel.remoteAddress() + "异常");
        cause.printStackTrace();
        channel.close();
    }


}
