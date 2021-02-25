package com.dj.sometest.test;

import cn.hutool.core.net.NetUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: Chris
 * @Date: 2021/2/19 21:32
 */
public class IPTest {

    public static void main(String[] args) throws UnknownHostException {
        long workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        InetAddress host = InetAddress.getLocalHost();
        String hostAddress = host.getHostAddress();
        System.out.println(workId);
    }
}
