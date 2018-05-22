package com.longrise.jie.dynamicproxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RealSubject realSubject = new RealSubject();
        //InvocationHandler handler = new DynamicProxy(realSubject);

        InvocationHandler handler1 = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.e("gpj", "Before calling Method " + method);
                method.invoke(realSubject, args);
                Log.e("gpj", "After calling");
                return null;
            }
        };

        Subject subject = (Subject) Proxy.newProxyInstance(handler1.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler1);

        Log.e("gpj", subject.getClass().getName());
        subject.doSomething();
        subject.sayHello("gpj000");
    }
}
