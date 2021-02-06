package com.dj.sometest.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author: Chris
 * @Date: 2021/2/6 13:42
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //得到管道
        ChannelPipeline pipeline = ch.pipeline();

        //加入一个netty提供的http编解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());

        //添加一个自定义handler
        pipeline.addLast("MyHttpServerHandler",new MyHttpServerHandler());

    }
}
