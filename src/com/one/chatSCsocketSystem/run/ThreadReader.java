package com.one.chatSCsocketSystem.run;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 读取客户端输入流多线程代码
 * */
public class ThreadReader extends Thread{

    Socket socket;
    public ThreadReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            while(true){
                InputStream ips = socket.getInputStream();
                byte[] b = new byte[1024];
                int len = ips.read(b);
                String str = new String(b,0,len);
                System.out.println(str);

                //自己加  关闭输入流
               //socket.shutdownInput();

               //关闭资源     不能写在这里
                //ips.close();
                //socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
