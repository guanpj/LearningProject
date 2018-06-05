package com.me.guanpj.jsample.dagger2sample.dagger.module;

import com.me.guanpj.jsample.dagger2sample.dagger.UserBean;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    UserBean provideUserBean() {
        return new UserBean();
    }
}
