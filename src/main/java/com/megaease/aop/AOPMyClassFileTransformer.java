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
        checkLoadedByLoader("org.apache.commons.lang3.StringUtils", loader);

        className = className.replaceAll("/", ".");
        try {
            CtClass ctClass = ClassPool.getDefault().get(className);
            CtMethod ctMethod = ctClass.getDeclaredMethod("doServiceTwo");
            if(!ctMethod.isEmpty()) {
                ctMethod.insertBefore("{System.out.println(\"insert log before doServiceTwo\"); }");
//                ctMethod.insertBefore("{org.apache.commons.lang3.StringUtils.isNotEmpty(\"\"); }");
            } else {
                System.out.println("method is empty");
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // check that the given class is loaded by the given loader
    static void checkLoadedByLoader(String name, ClassLoader loader) {
        try {
            Class<?> cl = Class.forName(name);
            ClassLoader actual = cl.getClassLoader();
            String loaderName = (actual == null) ? "boot class loader" : actual.toString();
            if (actual != loader) {
                System.out.println("FAIL: " + name + " incorrectly loaded by: " + loaderName);
            } else {
                System.out.println("PASS: " + name + " loaded by: " + loaderName);
            }
        } catch (Exception x) {
            System.out.println("FAIL: Unable to load " + name + ":" + x);
        }
    }


}
