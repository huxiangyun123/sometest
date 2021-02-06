package com.dj.sometest.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Author: Chris
 * @Date: 2021/2/6 13:49
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**读取客户端数据
     * HttpObject 表示客户端和服务端的数据格式封装为HttpObject
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest){

            HttpRequest httpRequest = (HttpRequest) msg;
            //获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }

            //回复 http协议
            ByteBuf content = Unpooled.copiedBuffer("hello 客户端,我是服务器", CharsetUtil.UTF_8);

            //构造一个http响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //发送
            ctx.writeAndFlush(response);

        }
    }
}
