package com.megaease.aop;

public class BaseMainClass {

    public static void main(String[] args) {
        BaseService baseService = new BaseServiceImpl();
        baseService.doServiceOne();
        baseService.doServiceTwo();
    }
}
