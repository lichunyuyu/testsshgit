package com.one.chatSocket.server;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2.java多线程Socket 编程通讯-实现聊天室代码
 * 服务器
 * https://www.cnblogs.com/jpwz/p/5705160.html
 * */
public class Server {

    private int port = 9000; //端口号
    private ServerSocket serverSocket ;//声明服务器    （服务器端需要监听特定端口的ServerSocket ,负责接收客户端的链接请求）
    private Socket socket ; // 声明客户端
    private String serverName;
    public Server(){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建服务器，开始监听
     *  */
    private void init() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("----服务器已开启----");
        System.out.println("请输入服务器名字：");
        //java.util.Scanner 主要功能是简化文本扫描。这个类最实用的地方表现在获取控制台输入， 当通过new Scanner(System.in)创建一个Scanner，控制台会一直等待输入，直到敲回车键结束，把所输入的内容传给Scanner，作为扫描对象。
        // 如果要获取输入的内容，则只需要调用Scanner的nextLine()方法即可。 http://bbs.itheima.com/thread-90856-1-1.html
        Scanner scanner = new Scanner(System.in);
        serverName = scanner.next();  //  查找并返回来自此扫描器的下一个完整标记
        while(true){
            socket = serverSocket.accept(); // 从连接请求队列中 取出一个连接
            hands(socket);
        }
    }

    private void hands(Socket socket){
        String key = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
        System.out.println("New connection accepted 监听到的客户端："+key);
        // 以下是 接收和发送数据
        Thread thread = new Thread(new ThreadSocket(socket));// 服务器处理Socket线程
        thread.setName(serverName);
        thread.start();
    }

    public static void main(String[] args){
        Server server = new Server();

    }
}

/**
 * http://blog.csdn.net/tian779278804/article/details/50922354
 * 线程池包括一个工作队列和若干工作线程。服务器程序向工作队列中加入与客户通信的任务，工作线程不断从工作队列中取出任务并执行它。
 * 本章还介绍了java.util.concurrent包中的线程池类的用法，在服务器程序中可以直接使用它们。
 * 3.1  构造ServerSocket
 ServerSocket的构造方法有以下几种重载形式：
 ◆ServerSocket()throws IOException
 ◆ServerSocket(int port) throws IOException
 ◆ServerSocket(int port, int backlog) throws IOException
 ◆ServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException
 在以上构造方法中，参数port指定服务器要绑定的端口（服务器要监听的端口），参数backlog指定客户连接请求队列的长度，参数bindAddr指定服务器要绑定的IP地址
 3.1.1  绑定端口
 除了第一个不带参数的构造方法以外，其他构造方法都会使服务器与特定端口绑定，该端口由参数port指定。例如，以下代码创建了一个与80端口绑定的服务器：
 [java] view plain copy
 ServerSocket serverSocket=new ServerSocket(80);
 ◆端口已经被其他服务器进程占用；如果运行时无法绑定到80端口，以上代码会抛出IOException，更确切地说，是抛出BindException，它是IOException的子类
 BindException一般是由以下原因造成的：
 ◆在某些操作系统中，如果没有以超级用户的身份来运行服务器程序，那么操作系统不允许服务器绑定到1~1023之间的端口。
 3.1.2  设定客户连接请求队列的长度
 当服务器进程运行时，可能会同时监听到多个客户的连接请求。例如，每当一个客户进程执行以下代码：
 [java] view plain copy
 Socket socket=new Socket(www.javathinker.org,80);
 就意味着在远程www.javathinker.org主机的80端口上，监听到了一个客户的连接请求。管理客户连接请求的任务是由操作系统来完成的。操作系统把这些连接请求存储在一个先进先出的队列中。许多操作系统限定了队列的最大长度，一般为50。当队列中的连接请求达到了队列的最大容量时，服务器进程所在的主机会拒绝新的连接请求。只有当服务器进程通过ServerSocket的accept()方法从队列中取出连接请求，使队列腾出空位时，队列才能继续加入新的连接请求。
 对于客户进程，如果它发出的连接请求被加入到服务器的队列中，就意味着客户与服务器的连接建立成功，客户进程从Socket构造方法中正常返回。如果客户进程发出的连接请求被服务器拒绝，Socket构造方法就会抛出ConnectionException。
 ServerSocket构造方法的backlog参数用来显式设置连接请求队列的长度，它将覆盖操作系统限定的队列的最大长度。值得注意的是，在以下几种情况中，仍然会采用操作系统限定的队列的最大长度：
 ◆backlog参数的值大于操作系统限定的队列的最大长度；
 ◆backlog参数的值小于或等于0；
 ◆在ServerSocket构造方法中没有设置backlog参数。
 * */
