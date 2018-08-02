package com.one.javatest.agent.dynamicclass;

/**
 * Created by vtstar on 2018/1/3.
 */

/**
 * （2）委托类的定义

 动态代理方式下，要求委托类必须实现某个接口，这里我们实现的是Sell接口。委托类Vendor类的定义如下：
 * */
public class Vendor implements Sell {
    @Override
    public void sell() {
        System.out.println("In sell method");
    }

    @Override
    public void ad() {
        System.out.println("In ad method");
    }
}
