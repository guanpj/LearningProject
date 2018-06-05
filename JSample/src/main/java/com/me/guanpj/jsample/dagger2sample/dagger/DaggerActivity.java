package com.me.guanpj.jsample.dagger2sample.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.me.guanpj.jsample.R;
import com.me.guanpj.jsample.dagger2sample.dagger.component.DaggerShoppingCartComponent;
import com.me.guanpj.jsample.dagger2sample.dagger.component.DaggerUserComponent;
import com.me.guanpj.jsample.dagger2sample.dagger.component.UserComponent;
import com.me.guanpj.jsample.dagger2sample.dagger.module.ShoppingCartModule;
import com.me.guanpj.jsample.dagger2sample.dagger.module.UserModule;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    @Inject
    UserBean userBean;
    @Inject
    ShoppingCartBean shoppingCartBean;
    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        userComponent = DaggerUserComponent.builder()
                .userModule(new UserModule())
                .build();
        //userComponent.inject(this);
        DaggerShoppingCartComponent.builder()
                .userComponent(userComponent)
                .shoppingCartModule(new ShoppingCartModule())
                .build()
                .inject(this);

        ((TextView) findViewById(R.id.text_view)).setText(
                userBean.getName() + "\n"
                        + userBean.getAge() + "\n"
                        + shoppingCartBean.getTotal());

    }
}
