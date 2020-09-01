package com.dj.sometest.util;

import com.dj.sometest.entity.Book;
import org.simpleframework.xml.core.Persister;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Chris
 * @Date: 2020/8/20 21:05
 */
public class JavaToXml {

    /**
     * xmlToObject
     */
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

    /**
     * ObjectToXml
     * @param obj
     * @param path
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //去掉xml第一行标识
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
