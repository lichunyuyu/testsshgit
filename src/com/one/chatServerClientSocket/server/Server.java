package com.one.chatServerClientSocket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vtstar on 2018/1/2.
 */
/**
 * 1.Java中使用Socket实现服务器端和客户端通讯
 * */
//创建服务器端serverSocket
public class Server {

    private int serverport = 8888;
    private ServerSocket serverSocket ;

    public Server(){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建服务器，开始监听
     * */
    private void init() throws IOException {
        //创建一个服务器端socket,指定绑定的端口号，并监听此端口
        serverSocket = new ServerSocket(serverport);
        //调用accept()方法开始监听，等待客户端的链接
        System.out.println("*****服务器即将启动，等待客户端的链接*****");
        Socket socket = serverSocket.accept();
        //获取输入流，等待客户端的链接
        InputStream inputStream = socket.getInputStream();
        //把字节流转换成字符流
        InputStreamReader isr = new InputStreamReader(inputStream);
        //为字符流增加缓冲区
        BufferedReader bfr = new BufferedReader(isr);
        String info = null;
        //循环读取数据
        while((info = bfr.readLine())!=null){
            System.out.println("我是服务器，客户端说："+info); // 谁写(输入)的，对方来读取（对对方来说，你写的是输入流，我来读）
        }
        //关闭输入流
        socket.shutdownInput();
        //向客户端传递信息
        OutputStream ops = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(ops);
        pw.write("欢迎登录!");
        pw.flush();

        //关闭资源
        pw.close();
        ops.close();
        bfr.close();
        isr.close();
        socket.close();
        serverSocket.close();
    }

    public static void main(String[] args){
        Server server = new Server();
    }
}

/**
 * 2. 字节流
 *  Java中的字节流处理的最基本单位为单个字节，它通常用来处理二进制数据。Java中最基本的两个字节流类是InputStream和OutputStream，它们分别代表了组基本的输入字节流和输出字节流。
 *   InputStream类中定义了一个基本的用于从字节流中读取字节的方法read，
 *   这是一个抽象方法，也就是说任何派生自InputStream的输入字节流类都需要实现这一方法，这一方法的功能是从字节流中读取一个字节，若到了末尾则返回-1，否则返回读入的字节。关于这个方法我们需要注意的是，它会一直阻塞知道返回一个读取到的字节或是-1。另外，字节流在默认情况下是不支持缓存的，这意味着每调用一次read方法都会请求操作系统来读取一个字节，这往往会伴随着一次磁盘IO，因此效率会比较低。
 *   实际上read(byte[])方法内部也是通过循环调用read()方法来实现“一次”读入一个字节数组的，因此本质来说这个方法也未使用内存缓冲区。要使用内存缓冲区以提高读取的效率，我们应该使用BufferedInputStream。
 *   3. 字符流
 *    Java中的字符流处理的最基本的单元是Unicode码元（大小2字节），它通常用来处理文本数据。所谓Unicode码元，也就是一个Unicode代码单元，范围是0x0000~0xFFFF。在以上范围内的每个数字都与一个字符相对应，Java中的String类型默认就把字符以Unicode规则编码而后存储在内存中。然而与存储在内存中不同，存储在磁盘上的数据通常有着各种各样的编码方式。使用不同的编码方式，相同的字符会有不同的二进制表示。实际上字符流是这样工作的：

 输出字符流：把要写入文件的字符序列（实际上是Unicode码元序列）转为指定编码方式下的字节序列，然后再写入到文件中；
 输入字符流：把要读取的字节序列按指定编码方式解码为相应字符序列（实际上是Unicode码元序列从）从而可以存在内存中。
 由于字符流在输出前实际上是要完成Unicode码元序列到相应编码方式的字节序列的转换，所以它会使用内存缓冲区来存放转换后得到的字节序列，等待都转换完毕再一同写入磁盘文件中。
 4. 字符流与字节流的区别
 字节流操作的基本单元为字节；字符流操作的基本单元为Unicode码元。
 字节流默认不使用缓冲区；字符流使用缓冲区。
 字节流通常用于处理二进制数据，实际上它可以处理任意类型的数据，但它不支持直接写入或读取Unicode码元；字符流通常处理文本数据，它支持写入及读取Unicode码元。
 * */
