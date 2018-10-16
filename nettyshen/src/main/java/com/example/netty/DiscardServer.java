package com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {

        //首先新建两个group   多线程事件循环器  netty有多重EventLoopGroup
        //  boss 用来接受 客户的连接   并且注册到 work上
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口" + port);

        //server bootstrap 是一个启动NIO的辅助启动类
        ServerBootstrap b = new ServerBootstrap();
        //将两个服务
        b = b.group(bossGroup, workGroup);
        //serverSocketChannel 以NIO的Selector为基础实现的    用来接受新的连接
        //这里告诉channel如何接受新的连接
        b = b.channel(NioServerSocketChannel.class);

        // ChannelInitiaLizer的作用是初始化一个channel 连接
        b = b.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                // 将新的连接规则 增加到ch上  pipeline  最后到channel    可增加多个
                ch.pipeline().addLast(new AppServerHandller());
//                 ch.pipeline().addLast(new TimeServerHandler());
            }
        });
        //你可以设置  指定的通道配置的参数    tcp/ip
        //因此可以允许设置  诸如 tcpDisplay   keepAlive 的参数
        b = b.option(ChannelOption.SO_BACKLOG, 128);

        //option 是提供给NioSocketChannel 用来接收进来的链接
        // childOption 是提供给  副管道接收到来的链接的
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);

        //绑定端口并去接收进来的链接
        ChannelFuture f = null;
        try {
            f = b.bind(port).sync();
            //这里会一直等待    知道socket被关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    //以上定义完了规则   将规则跑起来
    public static void main(String[] args) {
        int port ;
        if (args.length>0){
           port =Integer.parseInt(args[0]);
        }else {
            port=8088;
        }
        new DiscardServer(port).run();
        System.out.println("System.out:Run------");
    }


}
