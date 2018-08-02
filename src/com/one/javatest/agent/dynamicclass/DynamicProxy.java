package com.one.javatest.agent.dynamicclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * Created by vtstar on 2018/1/3.
 */

/**
 *（3）中介类

 上面我们提到过，中介类必须实现InvocationHandler接口，作为调用处理器”拦截“对代理类方法的调用。中介类的定义如下：
 * */
public class DynamicProxy implements InvocationHandler{

    private Object obj; // obj 为委托类对象

    public DynamicProxy(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // (com.one.javatest.agent.dynamicclass.Vendor@46f5f779 ,public abstract void com.one.javatest.agent.dynamicclass.Sell.sell(),null)
        System.out.println("before");
        // method --代理类 中的方法   -- 调用 委托类 对象的 相应方法
        Object result = method.invoke(obj,args);  // obj={Vendor@486}   到 Sell
        System.out.println("after");

        return result;
    }
}
/**
 *  从以上代码中我们可以看到，中介类持有一个委托类对象引用，在invoke方法中调用了委托类对象的相应方法（第26行），看到这里是不是觉得似曾相识？
 * 通过 聚合方式 持有委托类对象引用，把外部对invoke的调用最终都转为对委托类对象的调用。这不就是我们上面介绍的静态代理的一种实现方式吗？
 * 实际上，中介类与 委托类 构成了静态代理关系，在这个关系中，中介类 就是 代理类 ， 委托类 就是委托类；
 *
 * 代理类与中介类也构成一个静态代理关系，在这个关系中，中介类是委托类，代理类是代理类。
 * 也就是说，动态代理关系 是由 两组静态代理关系组成， 这也就是 冬天代理的 原理。 下面介绍一下 如何“指示” 以动态生成代理类。
 *
 * */
