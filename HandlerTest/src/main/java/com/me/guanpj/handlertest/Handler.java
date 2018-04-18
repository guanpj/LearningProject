package com.me.guanpj.handlertest;

/**
 * Created by Jie on 2017/8/5.
 */

public class Handler {
    MessageQueue mQueue;
    Looper mLooper;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread that has not called Looper.prepare()!");
        }
        mQueue = mLooper.mQueue;
    }

    public final void sendMessage(Message msg) {
        if(mQueue != null) {
            msg.target = this;
            mQueue.enqueueMessage(msg);
        }else {
            throw new RuntimeException(this + " sendMessage() called with no mQueue!");
        }
    }

    public void dispatchMessge(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {

    }
}
