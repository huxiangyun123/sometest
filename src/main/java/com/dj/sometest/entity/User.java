package com.dj.sometest.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: Chris
 * @Date: 2020/8/5 22:55
 */
@Data
public class User {

    private String name;

    private String habbit;

    private Integer age;

    public User(String name, String habbit) {
        this.name = name;
        this.habbit = habbit;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                habbit.equals(user.habbit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, habbit);
    }
}
