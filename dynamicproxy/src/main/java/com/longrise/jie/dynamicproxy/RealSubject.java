package com.longrise.jie.dynamicproxy;

import android.util.Log;

/**
 * Created by Jie on 2016/8/12.
 */
public class RealSubject implements Subject
{
    @Override
    public void doSomething()
    {
        Log.e("gpj", "I have do something already!");
    }

    @Override
    public void sayHello(String str)
    {
        if(null != str)
        {
            Log.e("gpj", "Hello :" + str);
            return;
        }
        Log.e("gpj", "Hello Jie!");
    }
}
