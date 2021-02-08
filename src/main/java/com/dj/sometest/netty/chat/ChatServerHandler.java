package com.dj.sometest.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: Chris
 * @Date: 2021/2/7 23:25
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 客户端上线即调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //该方法遍历所有channel，发送数据
        channelGroup.writeAndFlush("[ 客户端 ]" + channel.remoteAddress() + "上线了");
        channelGroup.add(channel);
        System.out.println("[ 客户端 ]" + channel.remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[ 客户端 ]" + channel.remoteAddress() + "下线了");
        System.out.println("[ 客户端 ]" + channel.remoteAddress() + "下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("[ 客户端 ]" + ctx.channel().remoteAddress() + "发送了 : " + msg);
        Channel sf = ctx.channel();
        for (Channel channel : channelGroup) {
            if (channel != sf) {
                channel.writeAndFlush("[ 客户端 ]" + channel.remoteAddress() + "发送了 : " + msg);
            }
        }
    }
}
