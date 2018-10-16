package com.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleChatClientHandler {


    public static void main(String[] args) {
        new SimpleChatClientHandler("localhost", 8080).run();
    }

    private final String host;

    private final int port;

    public SimpleChatClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void run() {

        NioEventLoopGroup loop = new NioEventLoopGroup();

        Bootstrap b;
        b = new Bootstrap();
        b.group(loop).channel(NioSocketChannel.class).handler(new SimpleChatClientInitialzer());

        Channel channel = null;
        try {
            channel = b.connect(host, port).sync().channel();
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(buf.readLine() + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            loop.shutdownGracefully();
        }


    }


}
