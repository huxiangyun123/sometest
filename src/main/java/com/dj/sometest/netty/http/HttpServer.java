package com.dj.sometest.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Chris
 * @Date: 2021/2/6 13:41
 */
public class HttpServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyChannelInitializer());

            System.out.println("服务器 is ok ...");

            ChannelFuture channelFuture = serverBootstrap.bind(7001).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
