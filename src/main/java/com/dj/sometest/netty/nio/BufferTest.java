package com.dj.sometest.netty.nio;

import java.nio.IntBuffer;

/**
 * @Author: Chris
 * @Date: 2020/12/19 17:05
 */
public class BufferTest {
    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(5);

        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i * 2);
        }

        //翻转 limit = position;position=0；
        intBuffer.flip();

        for(int i=0;i<intBuffer.capacity();i++){
            System.out.println(intBuffer.get());
        }

    }
}
