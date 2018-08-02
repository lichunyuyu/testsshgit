package com.one.chatSCsocketSystem.run;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端发送数据多线程代码
 * */
public class ThreadWriter extends Thread{

    Socket socket;
    public ThreadWriter(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            Scanner scanner = new Scanner(System.in);
            while(true){
                String str = scanner.next();
                OutputStream os = socket.getOutputStream();
                os.write(str.getBytes());
                os.flush();
                //自己加的 关闭输出流
                //socket.shutdownOutput();

                //关闭资源
                //os.close();
                //scanner.close();
                //socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
