package com.one.chatServerClientSocket.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by vtstar on 2018/1/2.
 */

//创建客户端 Socket
public class Client {

    private int port = 8888;
    private String ip = "127.0.0.1";

    public Client() throws IOException {
        init();
    }
    public void init() throws IOException {
        //创建一个客户端Socket
        Socket socket = new Socket(ip,port);
        //向服务器端传递信息
        OutputStream opt = socket.getOutputStream();
//        Scanner scanner = new Scanner(System.in);
//        opt.write(scanner.next().getBytes());
//        opt.flush();
        PrintWriter pw = new PrintWriter(opt);
        pw.write("用户名:admin;密码:123");
        pw.flush();

        //关闭输出流
        socket.shutdownOutput();
        //获取服务器端传递的数据
        InputStream ips = socket.getInputStream();
        //将字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(ips);
        //给字符流添加缓冲区
        BufferedReader bfr = new BufferedReader(isr);
        String info = null;
        while((info=bfr.readLine())!=null){
            System.out.println("我是客户端，服务器说:"+info);
        }
        //关闭资源
        bfr.close();
        isr.close();
        ips.close();
        pw.close();
        opt.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }
}
