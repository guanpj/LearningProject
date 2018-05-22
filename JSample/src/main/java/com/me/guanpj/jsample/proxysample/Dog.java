package com.me.guanpj.jsample.proxysample;

import android.util.Log;

public class Dog implements Animal {
    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public void doSomething(String action) {
        if (action.equals("fly")) {
            Log.e("gpj", "dog is can not flying");
        } else if (action.equals("run")) {
            Log.e("gpj", "dog is running");
        }
    }
}
