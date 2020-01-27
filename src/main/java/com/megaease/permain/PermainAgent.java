package com.megaease.permain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PermainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> clazz,
                                    ProtectionDomain protectionDomain,
                                    byte[] byteCode) throws IllegalClassFormatException {

//                clazz.getMethods();

                System.out.println(String.format("Process by ClassFileTransformer,target class = %s", className));
                return byteCode;
            }
        });
    }
}
