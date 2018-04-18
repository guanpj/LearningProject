package com.longrise.jie.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLocal;
    Button btnRemote;
    TextView tvResult;
    IAidlInterface mStub;
    IMyAidlInterface mMyStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLocal = (Button) findViewById(R.id.btn_mine);
        btnLocal.setOnClickListener(this);

        btnRemote = (Button) findViewById(R.id.btn_auto);
        btnRemote.setOnClickListener(this);

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
            /*mStub = IAidlInterface.Stub.asInterface(service);
            if (null != mStub) {
                try {
                    tvResult.setText("调用了自动生成的AIDL对象:" + mStub.add(1, 2));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }*/

            mMyStub = IMyAidlInterface.Stub.asInterface(service);
            if (null != mMyStub) {
                try {
                    tvResult.setText("调用了自己的AIDL对象:" + mMyStub.add(1, 2));
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

        if (v.getId() == R.id.btn_mine) {
            intent.putExtra("action", 1);
        } else if (v.getId() == R.id.btn_auto) {
            intent.putExtra("action", 2);
        }
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }
}
