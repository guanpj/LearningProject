package com.me.guanpj.jsample.proxysample;

import android.util.Log;

public class Bird implements Animal {
    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public void doSomething(String action) {
        if (action.equals("fly")) {
            Log.e("gpj", "bird is flying");
        } else if (action.equals("run")) {
            Log.e("gpj", "bird is also can run");
        }
    }
}
