package com.dj.sometest.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Chris
 * @Date: 2021/1/17 18:25
 */
public class GroupChatServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public GroupChatServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(6667));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        while (true) {
            //监控所有注册到selector上的通道，当有事件发生时，将对应的SelectionKey加入到selectedKeys集合中
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            //每个客户端注册到selector上就会有绑定一个SelectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            SelectionKey key = null;
            while (it.hasNext()) {
                key = it.next();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        //连接事件
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel accept = ssc.accept();
                        System.out.println("客户端上线===>" + accept.getLocalAddress().toString());
                        accept.configureBlocking(false);
                        //客户端socket连接注册到selector上
                        accept.register(selector, SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        //读事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(buffer);
                        if (read > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String msg = new String(bytes);
                            System.out.println("接收到数据===>" + msg);
                            //转发
                            dispatch(socketChannel,msg);
                        }
                    }
                    it.remove();
                }
            }
        }
    }

    public void dispatch(SocketChannel self,String msg) throws IOException {
        for (SelectionKey selectionKey : selector.selectedKeys()) {
            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel && channel != self){
                SocketChannel dest = (SocketChannel)channel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
