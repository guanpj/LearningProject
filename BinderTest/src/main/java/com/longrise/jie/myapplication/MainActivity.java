package com.longrise.jie.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLocal;
    TextView tvResult;
    IMyAidlInterface mMyStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLocal = (Button) findViewById(R.id.btn_mine);
        btnLocal.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.txt_result);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConn);
        super.onDestroy();
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" +"服务已绑定");
            mMyStub = IMyAidlInterface.Stub.asInterface(service);
            if (null != mMyStub) {
                try {
                    Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" +"开始调用add");
                    int add = mMyStub.add(1, 2);
                    tvResult.setText("调用返回结果:" + add);
                    Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" +"调用结果：" + add);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction("com.longrise.jie.myapplication");
        intent.setComponent(new ComponentName("com.longrise.jie.myapplication", "com.longrise.jie.myapplication.MyServer"));

        Log.e("gpj", "线程：" + Thread.currentThread().getName() + "————" + "开始绑定服务");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }
}
