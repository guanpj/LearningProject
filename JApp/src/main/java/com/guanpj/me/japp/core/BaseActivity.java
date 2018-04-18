package com.guanpj.me.japp.core;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.guanpj.me.japp.R;
import com.guanpj.me.japp.common.ConstantValues;
import com.guanpj.me.japp.sample.WelcomeActivity;
import com.guanpj.me.japp.utils.ActivityCollector;
import com.guanpj.me.japp.utils.AppStatusTracker;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;
    private Class mTargetClass;
    private final static int REQUEST_CODE = 1;
    private static OnPermissionResultListener mListener;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        switch (AppStatusTracker.getInstance().getAppStatus()) {
            case ConstantValues.STATUS_FORCE_KILLED:
                protectApp();
                break;
            case ConstantValues.STATUS_KICK_OUT:
                kickOut();
                break;
            case ConstantValues.STATUS_LOGOUT:
            case ConstantValues.STATUS_OFFLINE:
            case ConstantValues.STATUS_ONLINE:
                setUpContentView();
                setUpView();
                setUpEvent();
                setUpData(savedInstanceState);
                break;
        }
    }

    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpEvent();

    protected abstract void setUpData(Bundle savedInstanceState);

    public void setTargetClass(Class targetClass){
        this.mTargetClass = targetClass;
    }

    protected void protectApp() {
        Log.e("gpj", "protectApp()");
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_RESTART_APP);
        startActivity(intent);
    }

    protected void kickOut() {
        Log.e("gpj", "kickOut()");
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_KICK_OUT);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, -1, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId) {
        setContentView(layoutResID, titleResId, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId, int mode) {
        setContentView(layoutResID, titleResId, -1, mode);
    }

    public void setContentView(int layoutResID, int titleResId, int menuId, int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResId, menuId, mode);
    }

    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        if (mode != MODE_NONE) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            mToolbar.setTitle("");
            mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);

            if (mode == MODE_BACK) {
                mToolbar.setNavigationIcon(R.mipmap.ic_action_return_white);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClicked();
                }
            });

            setUpTitle(titleResId);
            setUpMenu(menuId);
        }
    }

    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && mToolbarTitle != null) {
            mToolbarTitle.setText(titleResId);
        }
    }

    protected void setUpMenu(int menuId) {
        if (mToolbar != null){
            mToolbar.getMenu().clear();
            if (menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    protected void onNavigationBtnClicked() {
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public static void requestRunTimePermission(String[] permissions, OnPermissionResultListener listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
        } else {
            mListener.onPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onPermissionGranted();
                    } else {
                        mListener.onPermissionDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    public interface OnPermissionResultListener {
        void onPermissionGranted();
        void onPermissionDenied(List<String> deniedPermission);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
