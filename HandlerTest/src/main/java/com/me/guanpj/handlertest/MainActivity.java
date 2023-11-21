package com.me.guanpj.handlertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    com.me.guanpj.handlertest.MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Looper.prepare();
        //Looper.loop();
        handler = new com.me.guanpj.handlertest.MyHandler() {
            @Override
            public void handleMessage(Message msg) {
                Log.e("gpj", Thread.currentThread().getName() + "--receiver--" + msg.toString());
            }
        };
        findViewById(R.id.button).setOnClickListener(this);
    }

    class MyHandler extends android.os.Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, "bbb", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        MyHandler handler = new MyHandler();
        handler.sendEmptyMessage(1);
        /*handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*for (int i = 0; i < 10; i++) {
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
        }*/
    }
}
