package com.one.javatest.agent.dynamicclass;

/**
 * Created by vtstar on 2018/1/3.
 */

import java.lang.reflect.Proxy;

/**
 * （4）动态生成代理类

 动态生成代理类的相关代码如下：
 * */
public class Main {

    public static void main(String[] args){
        //创建中介类实例
        DynamicProxy inter = new DynamicProxy(new Vendor());
        //加上这句将会产生一个$Proxy0.class 文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");   // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\com\sun\proxy\$Proxy0.class
        //获取代理类实例sell
        Sell sell = (Sell)(Proxy.newProxyInstance(Sell.class.getClassLoader(),new Class[]{Sell.class},inter)); // (Proxy.newProxyInstance(ClassLoader, Class[], InvocationHandler))
        //通过代理对象 调用 代理类方法，实际上会转到invoke 方法调用
        sell.sell();  // sell :com.one.javatest.agent.dynamicclass.Vendor@46f5f779      inter={DynamicProxy@488} ->obj={Vendor@486}    到 DynamicProxy 。invoke 方法
        sell.ad();
    }
}
/**
 * 在以上代码中，我们调用Proxy类的newProxyInstance方法来获取一个代理类实例。这个代理类实现了我们指定的接口并且会把方法调用分发到指定的调用处理器。这个方法的声明如下：
 public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
 方法的三个参数含义分别如下：
 loader：定义了代理类的ClassLoder；
 interfaces：代理类实现的接口列表
 h：调用处理器，也就是我们上面定义的实现了InvocationHandler接口的类实例
 * */

/**结果：
 before
 In sell method
 after
 before
 In ad method
 after

 走 debug  时   ，会多 before after
 * */
