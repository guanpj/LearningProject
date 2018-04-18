package com.me.guanpj.jsample.dagger;

import dagger.Component;

@Component(dependencies = UserComponent.class, modules = ShoppingCartModule.class)
public interface ShoppingCartComponent {
    void inject(DaggerActivity activity);
}
