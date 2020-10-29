package com.dj.sometest.test;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

/**
 * @Author: Chris
 * @Date: 2020/9/27 11:14
 */
public class Test1 {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();

        for (URL urL : urLs) {
            System.out.println(urL);
        }
    }
}
