package com.megaease.aop;

import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class AOPPermainAgent {

    public static void premain(String options, Instrumentation ins) {
        try {
            JarFile jarFile = new JarFile("C:\\Documents\\commons-lang-2.6.jar");
            ins.appendToBootstrapClassLoaderSearch(jarFile);
            ins.appendToSystemClassLoaderSearch(jarFile);
            ins.addTransformer(new AOPMyClassFileTransformer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
