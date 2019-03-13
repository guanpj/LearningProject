package com.me.guanpj.handlertest;

/**
 * Created by Jie on 2017/8/4.
 */

public class Message {
    public int what;
    public Object obj;
    public MyHandler target;

    @Override
    public String toString() {
        return obj.toString();
    }
}
