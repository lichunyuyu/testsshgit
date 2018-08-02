package com.one.javatest.agent.dynamicclass;

/**
 * Created by vtstar on 2018/1/3.
 */

import java.lang.reflect.Method;

/**
 * 2. 使用动态代理

 （1）InvocationHandler接口

 在使用动态代理时，我们需要定义一个位于代理类与委托类之间的中介类，这个中介类被要求实现InvocationHandler接口，这个接口的定义如下：
 * */
public interface InvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}

/**
 * 其实：import java.lang.reflect.InvocationHandler; 有
 * 从InvocationHandler这个名称我们就可以知道，实现了这个接口的中介类用做“调用处理器”。
 * 当我们调用代理类对象的方法时，这个“调用”会转送到invoke方法中，
 * 代理类对象作为proxy参数传入，参数method标识了我们具体调用的是代理类的哪个方法，args为这个方法的参数。
 *
 *这样一来，我们对代理类中的所有方法的调用都会变为对invoke的调用，这样我们可以在invoke方法中添加统一的处理逻辑（也可以根据method参数对不同的代理类方法做不同的处理）。因此我们只需在中介类的invoke方法实现中输出“before”，然后调用委托类的invoke方法，再输出“after”。下面我们来一步一步具体实现它。
 * */