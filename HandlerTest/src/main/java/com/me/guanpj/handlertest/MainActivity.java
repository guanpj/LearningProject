package com.me.guanpj.handlertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Looper.prepare();
        Looper.loop();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.e("gpj", Thread.currentThread().getName() + "--receiver--" + msg.toString());
            }
        };
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message();
                        msg.what = 0;
                        synchronized (UUID.class) {
                            msg.obj = Thread.currentThread().getName()+"--send---"+ UUID.randomUUID().toString();
                        }
                        Log.e("gpj", msg.toString());
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }
            }).start();
        }
    }
}
