package com.test.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleChatClient extends SimpleChannelInboundHandler<String> {


    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

        System.out.println(s+"[client]...");
    }


}
