package com.longrise.jie.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by Jie on 2016-7-26.
 */
public class MyServer extends Service {
    IAidlInterface.Stub mStub = new IAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    IMyAidlInterface.Stub mMyStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return 2 * a + 2 * b;
        }
    };

    class MyStub extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return 2 * a + 2 * b;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int action = intent.getIntExtra("action", 1);
        if (action == 1) {
            return mMyStub;
        } else if (action == 2) {
            return mStub;
        }
        return null;
    }
}
