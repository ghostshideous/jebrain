package com.test.chatapp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 启动服务类
 */
public class SimpleChatServer {

    private int port;

    public SimpleChatServer(int port) {
        this.port = port;
    }


    public void run() throws InterruptedException {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChatServerInitialzer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        System.out.println("SimpleSever 启动了");

        //绑定端口 等待  介入的channel
        ChannelFuture future = b.bind(port).sync();
        //等待  直到socket关闭    优雅的关闭
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        int port;
        if (args.length>0) {
            port = Integer.parseInt(args[0]);
        }else {
            port=8080;
        }
        new SimpleChatServer(port).run();
    }

}
