package com.one.javatest.agent.dynamicclass;

/**
 * Created by vtstar on 2018/1/3.
 */

/**
 * 二、动态代理
 1. 什么是动态代理
 代理类在程序运行时创建的代理方式被成为动态代理。也就是说，这种情况下，代理类并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的。相比于静态代理，动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类的函数。

 现在，假设我们要实现这样一个需求：在执行委托类中的方法之前输出“before”，在执行完毕后输出“after”。
 首先我们来使用静态代理来实现这一需求，代码--staticclass--BussinessAgent
 * */
public interface Sell {
    void sell();
    void ad();
}
