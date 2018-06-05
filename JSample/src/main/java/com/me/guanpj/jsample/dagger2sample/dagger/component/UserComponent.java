package com.me.guanpj.jsample.dagger2sample.dagger.component;

import com.me.guanpj.jsample.dagger2sample.dagger.UserBean;
import com.me.guanpj.jsample.dagger2sample.dagger.module.UserModule;

import dagger.Component;

@Component(modules = UserModule.class)
public interface UserComponent {
    /*void inject(DaggerActivity activity);*/
    UserBean getUserBean();
}
