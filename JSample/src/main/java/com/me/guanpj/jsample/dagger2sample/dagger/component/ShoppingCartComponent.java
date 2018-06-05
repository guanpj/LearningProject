package com.me.guanpj.jsample.dagger2sample.dagger.component;

import com.me.guanpj.jsample.dagger2sample.dagger.DaggerActivity;
import com.me.guanpj.jsample.dagger2sample.dagger.module.ShoppingCartModule;

import dagger.Component;

@Component(dependencies = UserComponent.class, modules = ShoppingCartModule.class)
public interface ShoppingCartComponent {
    void inject(DaggerActivity activity);
}
