package com.one.chatSocket.server;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取输入流线程
 * */
public class ThreadReader implements Runnable {

    //private static int HEAD_SIZE = 5; //传输最大字符串
    //private static int BUFFER_SIZE = 10;//每次读取10个字节
    private InputStream inputStream;
    public ThreadReader(InputStream inputStream){
        this.inputStream = inputStream;
    }
    @Override
    public void run() {
//        while(true){
//            byte[] bytes = new byte[1024];
            try {
                while(true){
                byte[] bytes = new byte[1024];
                int length = inputStream.read(bytes);
                System.out.println("--read.length="+length);
                String message = new String(bytes,0,length);
                System.out.println("读取输入流线程---当前线程名字+message："+Thread.currentThread().getName()+":"+ message);
            }
        }catch (IOException e) {
                e.printStackTrace();
            }
    }
}

/**
 * 继承Thread和实现Runnable实现一个线程过程都差不多；
 调用启动有些区别：
 * 我们查看Thread源码发现，他需要一个Runnable类型的对象；
 * public Thread(Runnable target){
 *     init(null,target,"Thread"+nextThreadNum(),0);
 * }
 *
 * 总结：
 实现Runnable接口比继承Thread类所具有的优势：

 1）：适合多个相同的程序代码的线程去处理同一个资源

 2）：可以避免java中的单继承的限制

 3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独
 * */