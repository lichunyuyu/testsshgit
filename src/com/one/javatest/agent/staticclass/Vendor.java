package com.one.javatest.agent.staticclass;

/**
 * Created by vtstar on 2018/1/3.
 */

/**
 * 厂家   --委托类
 * */
public class Vendor implements Sell{


    @Override
    public void sell() {
        System.out.println("In sell method");
    }

    @Override
    public void ad() {
        System.out.println("in ad method");
    }
}
