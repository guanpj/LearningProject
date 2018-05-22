package com.me.guanpj.jsample.hooksample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class HookActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ClipHelper.binder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EditText editText = new EditText(this);
        setContentView(editText);
    }
}
