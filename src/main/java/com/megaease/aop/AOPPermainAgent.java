package com.megaease.aop;

import java.lang.instrument.Instrumentation;

public class AOPPermainAgent {

    public static void premain(String options, Instrumentation ins) {
        try {
            ins.addTransformer(new AOPMyClassFileTransformer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
