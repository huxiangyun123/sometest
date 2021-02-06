package com.dj.sometest.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: Chris
 * @Date: 2021/1/17 19:52
 */
public class GroupChatClient {

    private String host = "127.0.0.1";
    private int port = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
    }

    public void sendInfor(String info) throws IOException {
        info = username + ": 说 "+ info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    public void read() throws IOException {
        int select = selector.select();
        if(select > 0){
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    String msg = new String(buffer.array());
                    System.out.println(msg);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        GroupChatClient client = new GroupChatClient();

        new Thread(() -> {
            while (true){
                try {
                    client.read();
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            client.sendInfor(s);
        }
    }

}
