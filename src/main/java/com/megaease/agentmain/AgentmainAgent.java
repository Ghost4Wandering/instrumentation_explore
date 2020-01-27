package com.megaease.agentmain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentmainAgent {

    private static Instrumentation INST;

    public static void agentmain(String agentArgs, Instrumentation inst) {
        INST = inst;
        process();
    }

    private static void process() {
        INST.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> clazz,
                                    ProtectionDomain protectionDomain,
                                    byte[] byteCode) throws IllegalClassFormatException {
                System.out.println(String.format("Agentmain process by ClassFileTransformer,target class = %s", className));
                return byteCode;
            }
        }, true);
        try {
            INST.retransformClasses(Class.forName("com.megaease.agentmain.sample.AgentTargetSample"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
