package com.megaease.aop.sample;

public class BaseServiceImpl implements BaseService {

    @Override
    public void doServiceOne() {
        System.out.println("do first service method");
    }

    @Override
    public void doServiceTwo() {
        System.out.println("do second service method");
    }
}
