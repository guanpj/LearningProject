package com.guanpj.me.japp.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.guanpj.me.japp.common.ConstantValues;

/**
 * Created by Jie on 2017-3-7.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

    private static AppStatusTracker tracker;
    private int mActiveCount;
    private boolean mIsForeGround;
    private int mAppStatus = ConstantValues.STATUS_ONLINE;

    private AppStatusTracker(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public void setAppStatus(int status) {
        this.mAppStatus = status;
    }

    public int getAppStatus() {
        return this.mAppStatus;
    }

    public boolean isForground() {
        return mIsForeGround;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        mActiveCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mIsForeGround = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActiveCount--;
        if (mActiveCount == 0) {
            mIsForeGround = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
