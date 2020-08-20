package com.dj.sometest.util;

import com.dj.sometest.entity.Book;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chris
 * @Date: 2020/8/20 21:05
 */
public class JavaToXml {

    public static void toObject() {
        // 输入流
        File file = new File("b.xml");
        // 持久化工具
        Persister persister = new Persister();

        // file ---> Object(Book.class)
        try {
            Book book = persister.read(Book.class, file);
            System.out.println(book.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void toXml(Book book) {

        // 持久化类
        Persister persister = new Persister();

        try {
            persister.write(book, new File("D:\\b.xml"));
            persister.write(book, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
