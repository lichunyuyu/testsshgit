package com.one.javatest.agent.staticclass;

/**
 * Created by vtstar on 2018/1/3.
 */

/**
 * 微商  --代理类
 * */
public class BussinessAgent implements Sell {

    // 聚合 （BussinessAgent 包含 Vendor ，但 Vendor 也可以单独存在）  静态代理可以通过聚合来实现，让代理类持有一个委托类的引用即可。
    private Vendor mvendor;

    public BussinessAgent(Vendor vendor){
        mvendor = vendor;
        //
        //this.mvendor = vendor;
    }
    @Override
    public void sell() {
        System.out.println("before");
        //mvendor.sell();
        // 只卖货给大学生  (相当于为 厂家Vendor做了一次过滤 ，但不用修改 委托类--厂家 代码)
        if(isCollegeStudent()){
            mvendor.sell();
        }
        System.out.println("after");
    }

    @Override
    public void ad() {
        System.out.println("before");
        mvendor.ad();
        System.out.println("after");
    }
    //判断是否大学生
    private boolean isCollegeStudent(){
        //..
        return true;
    }
}
/**
 *  从BusinessAgent类的定义我们可以了解到，静态代理可以通过聚合来实现，让代理类持有一个委托类的引用即可。

 下面我们考虑一下这个需求：给Vendor类增加一个过滤功能，只卖货给大学生。通过静态代理，我们无需修改Vendor类的代码就可以实现，只需在BusinessAgent类中的sell方法中添加一个判断即可如下所示：
 * */
/**
 *  这对应着我们上面提到的使用代理的第二个优点：可以实现客户与委托类间的解耦，在不修改委托类代码的情况下能够做一些额外的处理。静态代理的局限在于运行前必须编写好代理类，下面我们重点来介绍下运行时生成代理类的动态代理方式。
 * */

/**
 * ：在执行委托类中的方法之前输出“before”，在执行完毕后输出“after”。我们还是以上面例子中的Vendor类作为委托类，BusinessAgent类作为代理类来进行介绍。首先我们来使用静态代理来实现这一需求
 * 二 。2 从以上代码中我们可以了解到，通过静态代理实现我们的需求需要我们在每个方法中都添加相应的逻辑，这里只存在两个方法所以工作量还不算大，假如Sell接口中包含上百个方法呢？这时候使用静态代理就会编写许多冗余代码。通过使用动态代理，我们可以做一个“统一指示”，从而对所有代理类的方法进行统一处理，而不用逐一修改每个方法。下面我们来具体介绍下如何使用动态代理方式实现我们的需求。
 * */