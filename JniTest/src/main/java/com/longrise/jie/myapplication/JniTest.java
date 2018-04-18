package com.longrise.jie.myapplication;

/**
 * Created by Jie on 2017/7/19.
 */

public class JniTest
{
    static
    {
        System.loadLibrary("JniTest");
    }
    public static native String getStringFromJni();
}
