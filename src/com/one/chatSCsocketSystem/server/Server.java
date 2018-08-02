package com.one.chatSCsocketSystem.server;

import com.one.chatSCsocketSystem.run.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by vtstar on 2018/1/2.
 */

/**
 * Java多线程Socket在控制台输出的多人聊天室编程  -- 用system.out.println
 * */
public class Server {

    private static ArrayList<Socket> list = new ArrayList<Socket>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Socket socket = serverSocket.accept();
            list.add(socket);
            new ServerThread(socket,list).start();

            //关闭资源
           // serverSocket.close();
        }
    }
}
