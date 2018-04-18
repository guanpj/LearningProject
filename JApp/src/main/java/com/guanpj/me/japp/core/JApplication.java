package com.guanpj.me.japp.core;

import android.app.Application;

import com.guanpj.me.japp.utils.AppStatusTracker;

/**
 * Created by Jie on 2017-3-7.
 */

public class JApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppStatusTracker.init(this);
    }
}
