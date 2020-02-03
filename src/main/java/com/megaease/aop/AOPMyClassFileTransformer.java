package com.megaease.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class AOPMyClassFileTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (!"com/megaease/aop/BaseServiceImpl".equals(className)) {
            return null;
        }
        className = className.replaceAll("/", ".");
        try {
            CtClass ctClass = ClassPool.getDefault().get(className);
            CtMethod ctMethod = ctClass.getDeclaredMethod("doServiceTwo");
            if(!ctMethod.isEmpty()) {
                ctMethod.insertBefore("{System.out.println(\"insert log before doServiceTwo\"); }");
                ctMethod.insertAfter("{System.out.println(\"insert log after doServiceTwo\"); }");
            } else {
                System.out.println("method is empty");
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
