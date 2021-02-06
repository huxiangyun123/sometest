package com.dj.sometest.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Chris
 * @Date: 2020/12/19 17:08
 */
public class ChannelTest {

    public static void main(String[] args) throws Exception {

        String s = "hello world";

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\channel.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(s.getBytes());
        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();
    }
}
