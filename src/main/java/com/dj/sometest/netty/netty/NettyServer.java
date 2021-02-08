package com.dj.sometest.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Chris
 * @Date: 2021/2/6 11:16
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {

        //1.创建2个线程组
        //2.bossGroup仅处理连接请求
        //3.workGroup进行真正的业务处理
        //4.两个组都是无限循环
        /**
         * bossGroup和workGroup含有的子线程(NioEventLoop)默认是CPU的核数 * 2
         * 每个NioEventLoop有自己的selector、selectedKeys
         */
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        //创建服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(parentGroup,childGroup)
                 //使用NioSocketChannel作为服务器的通道实现
                 .channel(NioServerSocketChannel.class)
                 //设置线程队列得到连接个数
                 .option(ChannelOption.SO_BACKLOG,128)
                 //设置保持活动连接状态
                 .childOption(ChannelOption.SO_KEEPALIVE,true)
                 //handler对应parentGroup,childHandler对应childGroup
                 .childHandler(new ChannelInitializer<SocketChannel>() {
                     //创建一个通道初始化对象
                     //给pipeline设置处理器
                     @Override
                     protected void initChannel(SocketChannel socketChannel) throws Exception {
                         //给workerGroup的EventLoopGroup设置管道处理器
                         socketChannel.pipeline().addLast(new NettyServerHandler());
                     }
                 });

        System.out.println("服务器 is ok ...");

        //绑定一个端口并且同步,返回一个channelFuture对象
        //启动服务器
        //bind是异步操作,sync()是为了同步等待该绑定操作完成
        ChannelFuture channelFuture = bootstrap.bind(6668).sync();

        //通过监听ChannelFuture的状态做出相应操作
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("绑定端口成功");
                }else {
                    System.out.println("绑定端口失败");
                }
            }
        });

        //对关闭通道进行监听
        //等待服务端关闭,sync()阻塞等待
        channelFuture.channel().closeFuture().sync();
    }
}
