package com.one.javatest.test.httpClientAuth;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vtstar on 2018/6/1.
 *
 * Httpclient 请求带Authorization（授权）的REST API 返回JSON数据
 * https://blog.csdn.net/XENIA1992/article/details/72887012
 *
 */
public class JavaNetURLRestFulClient {

    public static void main(String[] args) throws IOException {
        // 认证信息对象 ，用于包含访问翻译服务器的用户名和密码
        //String path = "http://192.168.0.101:8081/fap/rest/api/v3/tokens"; // 传的 user
        //String path = "http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs?IDs=100014378"; // ok
        String path = "http://192.168.0.101:8081/fap/rest/api/CalendarWeek/list"; // 需要 token  // 401 登录验证

        // 1 创建客户端访问服务器的httpclient 对象     打开浏览器
        HttpClient httpClient = new DefaultHttpClient();
        // 2. 以请求的连接地址创建get请求对象    浏览器中输入网址
        HttpGet httpGet = new HttpGet(path);

        // username:password ---> 访问用户名，密码   , 并使用base64编码进行加密，将加密的字节信息转化为string类型，encoding--->token
        //String encoding = DatatypeConverter.printBase64Binary("username:password".getBytes("UTF-8"));  // javax.xml.bind
        String encoding = DatatypeConverter.printBase64Binary("admin:dev".getBytes("UTF-8"));  // javax.xml.bind
        System.out.println("encoding......"+encoding);
        httpGet.setHeader("Authorization","Basic"+encoding);

//        String auth  = "Basic " + Base64.encode(("dev" + ":" + "dev").getBytes());
//        System.out.println("encoding......"+auth);
//        httpGet.addHeader("Authorization", auth);

        // 3. 向服务器端 发送请求 并且获取响应对象  浏览器中输入网址点击回车
        HttpResponse response = httpClient.execute(httpGet);
        // 4. 获取响应对象中的响应码
        StatusLine statusLine = response.getStatusLine();  // 获取请求对象中的响应行对象
        int responseCode = statusLine.getStatusCode(); // 从状态行中获取状态码

        System.out.println("responseCode......"+responseCode);  //  405 Request method 'GET' not supported
        if(responseCode==200){
            // 5. 可以接受发发送消息
            HttpEntity entity = response.getEntity();
            // 6. 从消息载体对象中获取操作的读取流对象
            InputStream input = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String str1 = br.readLine();
            String result = new String(str1.getBytes("gbk"),"utf-8");
            System.out.println("服务器的响应是:" + result);
            br.close();
            input.close();
        }else {
            System.out.println("响应失败!");
        }

    }
}
