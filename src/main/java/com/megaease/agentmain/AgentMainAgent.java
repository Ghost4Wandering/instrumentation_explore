package com.megaease.agentmain;

import com.megaease.agentmain.sample.DynamicClassLoader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMainAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> clazz,
                                    ProtectionDomain protectionDomain,
                                    byte[] byteCode) {

                try {
                    File f = new File("C:\\Documents\\AgentMainService.class");
                    byte[] targetClassFile = new byte[(int) f.length()];
                    DataInputStream dis = new DataInputStream(new FileInputStream(f));
                    dis.readFully(targetClassFile);

                    return targetClassFile;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return byteCode;
            }
        }, true);
        try {
            inst.retransformClasses(Class.forName("com.megaease.agentmain.sample.AgentMainService"));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        try {
//            File f = new File("C:\\Documents\\AgentMainService.class");
//            byte[] targetClassFile = new byte[(int) f.length()];
//            DataInputStream dis = new DataInputStream(new FileInputStream(f));
//            dis.readFully(targetClassFile);
//            dis.close();
//
//            DynamicClassLoader myLoader = new DynamicClassLoader();
//            Class<?> targetClazz = myLoader.findClass(targetClassFile);
//            System.out.println("The target class class full path is " + targetClazz.getName());
//            ClassDefinition clazzDef = new ClassDefinition(Class.forName(targetClazz.getName()), targetClassFile);
//            inst.redefineClasses(clazzDef);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
