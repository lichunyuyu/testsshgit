package com.one.chatSCsocketSystem.client;

import com.one.chatSCsocketSystem.run.ThreadReader;
import com.one.chatSCsocketSystem.run.ThreadWriter;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by vtstar on 2018/1/2.
 */
public class Client {

//    private String ip = "127.0.0.1";
//    private int port = 9999;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);

        new ThreadWriter(socket).start();
        new ThreadReader(socket).start();

        //关闭资源   不能写在这里
        //socket.close();
    }
}
