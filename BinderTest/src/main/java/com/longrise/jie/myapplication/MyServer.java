package com.longrise.jie.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by Jie on 2016-7-26.
 */
public class MyServer extends Service {

    class MyStub extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "MyServer执行add");
            return 2 * a + 2 * b;
        }
    }

    private MyStub mStub = new MyStub();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "返回Stub");
        return mStub;
    }
}
