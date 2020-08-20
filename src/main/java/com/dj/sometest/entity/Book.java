package com.dj.sometest.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Chris
 * @Date: 2020/8/20 21:06
 */
@Data
public class Book {

    private String id;
    private String title;
    private Integer price;

    private List<String> authors;

    public Book(String id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
