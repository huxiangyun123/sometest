package com.dj.sometest.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Chris
 * @Date: 2021/1/23 17:24
 */
public class ConfigFileTest {
    public static void main(String[] args) throws IOException {

        Properties p = new Properties();

        InputStream is = ConfigFileTest.class.getClassLoader().getResourceAsStream("application.properties");

        p.load(is);

        String port = p.getProperty("server.port");

        System.out.println(port);
    }
}
