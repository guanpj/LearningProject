package com.me.guanpj.jsample.dagger2sample.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingCartModule {
    @Provides
    ShoppingCartModel provideShoppingCartModel() {
        return new ShoppingCartModel();
    }
}
