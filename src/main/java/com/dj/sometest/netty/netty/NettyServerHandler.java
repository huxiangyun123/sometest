package com.dj.sometest.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;


/**
 * @Author: Chris
 * @Date: 2021/2/6 11:41
 * 自定义handler需要继承ChannelInboundHandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端的消息
     *
     * @param ctx 上下文对象，含有管道PipeLine,通道Channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        //pipeline本质是一个双向链表
        ChannelPipeline pipeline = ctx.pipeline();
        Channel channel = ctx.channel();
        //将msg转成ByteBuf(netty提供的)
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息===>"+buf.toString(CharsetUtil.UTF_8));

        //业务逻辑时间长，异步处理
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 第二次",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 读取数据完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端",CharsetUtil.UTF_8));
    }


    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
