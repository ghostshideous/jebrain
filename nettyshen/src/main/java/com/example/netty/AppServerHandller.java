package com.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class AppServerHandller extends ChannelInboundHandlerAdapter {


    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try{
            while (true) {
                if (!(in.isReadable())) break;
//                String str = in.toString(CharsetUtil.UTF_8);
//                System.out.println(str);
//                System.out.flush();
                ctx.writeAndFlush(msg);
            }

        } finally {
            ReferenceCountUtil.release(in);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
