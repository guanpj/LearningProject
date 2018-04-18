package com.guanpj.me.japp.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.guanpj.me.japp.R;
import com.guanpj.me.japp.common.ConstantValues;
import com.guanpj.me.japp.core.BaseActivity;
import com.guanpj.me.japp.utils.AppStatusTracker;


/**
 * Created by Stay on 2/2/16.
 * Powered by www.stay4it.com
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_welcome, -1, MODE_NONE);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpEvent() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            finish();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }
}
