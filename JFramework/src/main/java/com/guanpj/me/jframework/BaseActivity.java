package com.guanpj.me.jframework;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.guanpj.me.jframework.utils.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2017-3-8.
 */

public class BaseActivity extends AppCompatActivity
{
    private static OnPermissionResultListener mListener;
    private final static int REQUEST_CODE = 1;

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

}
