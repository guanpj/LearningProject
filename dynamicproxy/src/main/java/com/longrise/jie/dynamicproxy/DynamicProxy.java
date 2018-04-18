package com.longrise.jie.dynamicproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Jie on 2016/8/12.
 */
public class DynamicProxy implements InvocationHandler
{
    private Object subject;

    public DynamicProxy(Object o)
    {
        this.subject = o;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        Log.e("gpj", "Before calling");
        Log.e("gpj", "Method " + method);
        method.invoke(subject, objects);
        Log.e("gpj", "After calling");
        return null;
    }
}
