package com.me.guanpj.jsample.proxysample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Proxy;

public class ProxySampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animal bird = new Bird();
        Animal dog = new Dog();
        DynamicProxy proxy = new DynamicProxy(dog);
        Animal animal = (Animal) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), bird.getClass().getInterfaces(), proxy);
        if (animal.canFly()) {
            animal.doSomething("fly");
        } else {
            animal.doSomething("run");
        }
    }
}
