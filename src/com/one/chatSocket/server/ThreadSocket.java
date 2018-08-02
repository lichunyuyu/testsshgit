package com.one.chatSocket.server;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.net.Socket;

/**
 * 服务器处理Socket 线程
 * */
public class ThreadSocket implements Runnable{

    private Socket socket;
    public ThreadSocket(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("服务器处理Socket 线程--run");
        try {
            Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()));// 读取输入流线程    客户端出水龙头
            Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
            threadReader.start();
            threadWriter.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
