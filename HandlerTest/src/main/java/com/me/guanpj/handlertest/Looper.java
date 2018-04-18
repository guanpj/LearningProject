package com.me.guanpj.handlertest;

/**
 * Created by Jie on 2017/8/5.
 */

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread!");
        }
        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        Looper me = sThreadLocal.get();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }

        MessageQueue queue = me.mQueue;

        for(;;) {
            Message msg = queue.next();
            if (msg == null || msg.target == null) {
                continue;
            }
            msg.target.dispatchMessge(msg);
        }
    }
}
