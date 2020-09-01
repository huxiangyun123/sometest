package com.dj.sometest.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Author: Chris
 * @Date: 2020/8/20 21:06
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "book")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "id",
        "title",
        "price",
        "authors"
})
public class Book {

    private String id;
    private String title;
    private Integer price;

    private List<User> authors;

    public Book(String id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Book() {
    }
}
