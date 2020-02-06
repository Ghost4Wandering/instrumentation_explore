package com.megaease.jni.sample;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class JNITransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        return classfileBuffer;
    }
}
