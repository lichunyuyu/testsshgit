package com.one.chatSocket.server;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * 发送数据线程
 * */
public class ThreadWriter implements Runnable {

    private OutputStream outputStream;
    public ThreadWriter(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
            try {
                System.out.println("发送数据线程ThreadWriter--请输入发送数据：");
                Scanner scanner = new Scanner(System.in);
                while (true){
                    String message = scanner.next();
                    System.out.println("发送数据线程--当前线程名字--："+Thread.currentThread().getName()+"说:"+message);
                    outputStream.write(message.getBytes());// 缓冲区  byte[]
                outputStream.flush(); // http://www.imooc.com/article/10791
            }
        }catch (IOException e) {
                e.printStackTrace();
         }
    }
}
