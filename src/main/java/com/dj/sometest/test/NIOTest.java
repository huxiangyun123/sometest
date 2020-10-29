package com.dj.sometest.test;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Chris
 * @Date: 2020/10/26 15:11
 */
public class NIOTest {

    private static ServerSocketChannel serverSocketChannel;

    //单线程操作selector
    private static Selector selector;

    public static void main(String[] args) throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(8081));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            SelectionKey key = null;
            while (it.hasNext()){
                key = it.next();
                handle(key);
                it.remove();
            }
        }

    }

    public static void handle(SelectionKey key) throws IOException {

        if(key.isValid()){
            if(key.isAcceptable()){
                //连接事件
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel accept = ssc.accept();
                System.out.println("接收到客户端===>" + accept);
                accept.configureBlocking(false);
                accept.register(selector,SelectionKey.OP_READ);
            }
            
            if(key.isReadable()){
                //读事件
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read = socketChannel.read(buffer);
                if(read > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    System.out.println("接收到数据===>"+ new String(bytes));
                }
                //写数据
            }
        }
    }














}
