package com.one.chatSocket.client;

/**
 * Created by vtstar on 2018/1/2.
 */

import com.one.chatSocket.server.ThreadReader;
import com.one.chatSocket.server.ThreadWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * */
public class Client {

    private int port = 9000;
    private String ip = "localhost";// 是本机测试的匹配地址  localhost
    private Socket socket;
    private String clientName ;
    public Client(){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        System.out.println("---客户端已开启---");
        System.out.println("请输入客户端名字：");
        Scanner scanner = new Scanner(System.in);
        clientName = scanner.next();

        socket = new Socket(ip,port);
    }

    public void hands() throws IOException {
        Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()),Thread.currentThread().getName());
        Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
        threadWriter.setName(clientName);
        threadReader.start();
        threadWriter.start();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.hands();
    }
}
