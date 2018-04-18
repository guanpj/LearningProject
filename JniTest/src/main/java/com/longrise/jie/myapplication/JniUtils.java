package com.longrise.jie.myapplication;

/**
 * Created by Jie on 2016-7-26.
 */
public class JniUtils
{
    static
    {
        System.loadLibrary("JniUtils");
    }

    public native String getValue();
}
