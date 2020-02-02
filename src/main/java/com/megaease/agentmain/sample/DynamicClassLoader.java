package com.megaease.agentmain.sample;

public class DynamicClassLoader extends ClassLoader{

    public Class<?> findClass(byte[] b) {
        return defineClass(null, b, 0, b.length);
    }
}
