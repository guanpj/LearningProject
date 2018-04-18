package com.me.guanpj.jsample.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    UserModel provideUserModel() {
        return new UserModel();
    }
}
