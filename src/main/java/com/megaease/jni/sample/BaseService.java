package com.megaease.jni.sample;

public class BaseService {

    static {
        System.loadLibrary("dll-jni");
    }

    public static native String doService(String str);

    public static native String test_doService(String str);
}
