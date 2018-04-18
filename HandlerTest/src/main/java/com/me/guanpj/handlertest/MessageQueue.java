package com.me.guanpj.handlertest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jie on 2017/8/4.
 */

public class MessageQueue {
    Lock mLock;
    Condition mEmptyQueue;
    Condition mFullQueue;

    int mCount;
    Message[] mMessages;
    int putIndex;
    int getIndex;

    public MessageQueue() {
        mMessages = new Message[50];
        mLock = new ReentrantLock();
        mEmptyQueue = mLock.newCondition();
        mFullQueue = mLock.newCondition();
    }

    final void enqueueMessage(Message msg) {
        try {
            mLock.lock();
            while (mCount == mMessages.length) {
                try {
                    mFullQueue.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mMessages[putIndex] = msg;
            putIndex = (++putIndex == mMessages.length ? 0 : putIndex);
            mCount++;
            mEmptyQueue.signalAll();
        } finally {
            mLock.unlock();
        }
    }

    final Message next() {
        Message msg = null;
        try {
            mLock.lock();
            while (mCount == 0) {
                try {
                    mEmptyQueue.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = mMessages[getIndex];
            getIndex = (++getIndex == mMessages.length ? 0 : getIndex);
            mCount--;
            mFullQueue.signalAll();
        } finally {
            mLock.unlock();
        }
        return msg;
    }
}
