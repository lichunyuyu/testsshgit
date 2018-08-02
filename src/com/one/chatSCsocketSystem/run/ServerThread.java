package com.one.chatSCsocketSystem.run;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by vtstar on 2018/1/2.
 */

/**
 * 服务器处理socket的多线程
 * */
public class ServerThread extends Thread {

    Socket socket ;
    ArrayList<Socket> list;
    InputStream is;
    OutputStream os;

    public  ServerThread(Socket socket,ArrayList<Socket> list){
        this.socket = socket;
        this.list = list;
    }

    @Override
    public void run(){
        try{
            while(true){
                is = socket.getInputStream();
                byte[] b =  new byte[1024];
                int len = is.read(b);
                String str = new String(b,0,len);
                System.out.println(str);
                for(Socket socket:list){
                    os = socket.getOutputStream();
                    os.write(str.getBytes());
                }
                //os.close();
                //is.close();
                //socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


/**
 * 一：线程与进程

 1 线程：进程中负责程序执行的执行单元
 线程本身依靠程序进行运行
 线程是程序中的顺序控制流，只能使用分配给程序的资源和环境

 2 进程：执行中的程序
 一个进程至少包含一个线程

 3 单线程：程序中只存在一个线程，实际上主方法就是一个主线程

 4 多线程：在一个程序中运行多个任务
 目的是更好地使用CPU资源
 * */