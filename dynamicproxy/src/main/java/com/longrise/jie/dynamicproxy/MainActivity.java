package com.longrise.jie.dynamicproxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.InvocationHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Subject realSubject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(realSubject);

        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        Log.e("gpj", subject.getClass().getName());
        subject.doSomething();
        subject.sayHello("gpj000");
    }
}
