package com.me.guanpj.jsample.proxysample;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("gpj", "before calling " + method.getName());
        if (method.getName().equals("doSomething")) {
            return method.invoke(object, new Object[]{"fly"});
        }
        return method.invoke(object, args);
    }
}
