package com.me.guanpj.jsample.dagger2research;

public class UserBean {
    private String name = "jie";
    private int age = 25;

    public UserBean(){}

    public UserBean(String name, int age) {
        this.name = name;
        this.age = age;
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
}
