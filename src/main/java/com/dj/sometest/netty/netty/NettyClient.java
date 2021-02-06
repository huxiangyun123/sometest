package com.dj.sometest.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Chris
 * @Date: 2021/2/6 11:55
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {

        //事件循环组
        EventLoopGroup group = new NioEventLoopGroup();

        //启动配置对象
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel socketChannel) throws Exception {
                         socketChannel.pipeline().addLast(new NettyClientHandler());
                     }
                 });

        System.out.println("客户端is ok ...");

        //启动客户端链接服务器
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();

        channelFuture.channel().closeFuture().sync();
    }
}
