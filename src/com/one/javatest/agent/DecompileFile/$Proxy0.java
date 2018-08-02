package com.one.javatest.agent.DecompileFile;

import com.one.javatest.agent.dynamicclass.Sell;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 使用  JD-JUI 反编译 工具 --得到该文件
 *
 * 3. 动态代理类的源码分析
 通过运行Main，我们会得到一个名为“$Proxy”的class文件，这个文件即为动态生成的代理类，我们通过反编译来查看下这个代理类的源代码：

 我们可以看到，以上代码的逻辑十分简单，我们在注释中也做出了相关的说明。（以上代码中涉及到反射的使用，对于反射还不是很熟悉的小伙伴可以参考这里：Java核心技术点之反射）
 现在，我们已经了解了动态代理的使用，也搞清楚了它的实现原理，更进一步的话我们可以去了解动态代理类的生成过程，只需要去阅读newProxyInstance方法的源码即可，这个方法的逻辑也没有复杂的地方，这里就不展开了。大家可以参考这篇文章：公共技术点之动态代理
 三、参考资料
 1. Java Docs
 2. 《深入理解Java虚拟机》
 3. 公共技术点之动态代理
 * */
public final class $Proxy0
  extends Proxy
  implements Sell
{
  // 这5个 Method 类 分别代表 equals()、toString()、ad()、sell()、 hashCode() 方法
  private static Method m1;
  private static Method m2;
  private static Method m3;
  private static Method m4;
  private static Method m0;

  // 构造方法 接收一个InvocationHandler 对象 为参数，这个对象就是代理类的 “直接委托类”（真正的委托类 可以看做 代理类的“间接委托类”）
  public $Proxy0(InvocationHandler paramInvocationHandler)
  {
    super(paramInvocationHandler);
  }

  // 对 equals 方法的调用实际上转为对super.h..invoke  方法的调用，父类中的 h 即为我们再构造方法中传入的InvocationHandler对象，
  // 以下的toString()、sell()、ad()、hashCode()等方法同理
  @Override
  public final boolean equals(Object paramObject)
  {
    try
    {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  @Override
  public final String toString()
  {
    try
    {
      return (String)this.h.invoke(this, m2, null);
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  @Override
  public final void ad()
  {
    try
    {
      this.h.invoke(this, m3, null);
      return;
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  @Override
  public final void sell()
  {
    try
    {
      this.h.invoke(this, m4, null);
      return;
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  @Override
  public final int hashCode()
  {
    try
    {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }

  // 这里完成 Method 对象的初始化（通过反射在运行时获得Method对象）
  static
  {
    try
    {
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      m3 = Class.forName("com.one.javatest.agent.dynamicclass.Sell").getMethod("ad", new Class[0]);
      m4 = Class.forName("com.one.javatest.agent.dynamicclass.Sell").getMethod("sell", new Class[0]);
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
//      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }
}

/**
 * 从中我们可以看出动态生成的代理类是以$Proxy为类名前缀，继承自Proxy，并且实现了Proxy.newProxyInstance(…)第二个参数传入的所有接口的类。
 如果代理类实现的接口中存在非 public 接口，则其包名为该接口的包名，否则为com.sun.proxy。
 其中的operateMethod1()、operateMethod2()、operateMethod3()函数都是直接交给h去处理，h在父类Proxy中定义为
 protected InvocationHandler h;
 即为Proxy.newProxyInstance(…)第三个参数。
 所以InvocationHandler的子类 C 连接代理类 A 和委托类 B，它是代理类 A 的委托类，委托类 B 的代理类。
 * */