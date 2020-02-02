package com.megaease.permain.sample;

public class PermainService {

    public void doServiceFirst() {
        System.out.println("=====>>>>>>");
    }

    public void doServiceSecord() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====>>>>> 2");
            }
        }).start();
    }


}
