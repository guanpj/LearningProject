package com.me.guanpj.jsample.dagger2sample.dagger.module;

import com.me.guanpj.jsample.dagger2sample.dagger.ShoppingCartBean;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingCartModule {
    @Provides
    ShoppingCartBean provideShoppingCartBean() {
        return new ShoppingCartBean();
    }
}
