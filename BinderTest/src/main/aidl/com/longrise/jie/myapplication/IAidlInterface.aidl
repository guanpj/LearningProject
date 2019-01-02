// IMyAidlInterface.aidl
package com.longrise.jie.myapplication;

import java.util.List;
import com.longrise.jie.myapplication.User;
// Declare any non-default types here with import statements

interface IAidlInterface {
    int add(int a, int b);

    void addUser(User user);

    List<User> getUserList();
}
