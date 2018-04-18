package com.guanpj.me.jframework.bean;

/**
 * Created by Jie on 2017-3-8.
 */

public class Person {

    private int header;
    private String name;
    private int age;

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "header: " + getHeader() + ", name: " + getName() + ", age: " + getAge();
    }
}
