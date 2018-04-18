//
// Created by Jie on 2016-7-26.
//

#include "com_longrise_jie_myapplication_JniUtils.h"
JNIEXPORT jstring JNICALL Java_com_longrise_jie_myapplication_JniUtils_getValue
        (JNIEnv *env, jobject obj) {
  return (*env)->NewStringUTF(env, "This is from jni");
}

