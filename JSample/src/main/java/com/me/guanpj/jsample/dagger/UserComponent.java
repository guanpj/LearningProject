package com.me.guanpj.jsample.dagger;

import dagger.Component;

@Component(modules = UserModule.class)
public interface UserComponent {
    //void inject(DaggerActivity activity);
    UserModel getUserModel();
}
