package com.me.guanpj.jsample.dagger;

import dagger.Module;

/**
 * Created by Jie on 2017/9/19.
 */

@Module
public class ShoppingCartModel {
    private String name = "jie";
    private int total = 100;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
