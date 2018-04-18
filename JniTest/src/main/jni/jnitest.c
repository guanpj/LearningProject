//
// Created by Jie on 2017/7/19.
//
#include "com_longrise_jie_myapplication_JniTest.h"
JNIEXPORT jstring JNICALL Java_com_longrise_jie_myapplication_JniTest_getStringFromJni
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "This is from jni!!!");
}
