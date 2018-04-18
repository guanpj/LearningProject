package com.guanpj.me.japp.module;

import com.guanpj.me.japp.core.BaseActivity;

/**
 * Created by Stay on 8/3/16.
 * Powered by www.stay4it.com
 */
public class Module {
    public String moduleName;
    public String moduleIcon;
    public Class<? extends BaseActivity> moduleTargetClass;

    public Module(String moduleName, Class<? extends BaseActivity> moduleTargetClass) {
        this.moduleName = moduleName;
        this.moduleTargetClass = moduleTargetClass;
    }
}
