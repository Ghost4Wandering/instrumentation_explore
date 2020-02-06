package com.megaease.jni;

import com.megaease.jni.sample.JNITransformer;

import java.lang.instrument.Instrumentation;

public class JNIPermainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        JNITransformer transformer = new JNITransformer();
        inst.addTransformer(transformer);
        inst.setNativeMethodPrefix(transformer, "test");
    }

}
