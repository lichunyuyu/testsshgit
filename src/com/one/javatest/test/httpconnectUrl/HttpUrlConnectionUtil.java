package com.one.javatest.test.httpconnectUrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by vtstar on 2018/5/31.
 *
 * 功能 ： httpUrlConnection 访问远程接口工具
 *
 */

public class HttpUrlConnectionUtil {

    /**
     *  方法体说明： 远程接口发起请求，返回字符串类型结果
     *  @param  url 接口地址
     *  @param  requestMethod 请求方式
     *  @param  params  传递参数    重点： 参数值需要 Base64进行转码
     *  @return  String  返回结果
     * */
    public static String httpRequestToString(String url,String requestMethod,Map<String,String> params){
        String result = null;
        try{
            InputStream is = httpRequestToStream(url,requestMethod,params);
            byte[] b = new byte[is.available()];
            is.read(b);
            result = new String(b);
        }catch(IOException e){
            e.printStackTrace();
        }
        return  result;
    }
    /**
     * 方法体说明：向远程接口发起请求，返回字节流类型结果
     * @param url 接口地址
     * @param requestMethod 请求方式
     * @param params 传递参数     重点：参数值需要用Base64进行转码
     * @return InputStream 返回结果
     * */
    public static InputStream httpRequestToStream(String url,String requestMethod,Map<String,String> params){
        InputStream is = null;
        try{
            String parameters  = "";
           // boolean hasParams = false;
            // 将参数集合拼接成特定格式，如 name=张三&age=24
//            for(String key : params.keySet()){
//                String value = URLEncoder.encode(params.get(key),"UTF-8");
//                parameter += key +"="+value+"&";
//                hasParams = true;
//            }

            // 将参数集合拼接成特定格式，如 name=张三&age=24
            if (params.size() == 1) {
                for (String key : params.keySet()) {
                    String value = URLEncoder.encode(params.get(key),"UTF-8");
                    parameters  += key +"="+value;
                }
            } else {
                for (String key : params.keySet()) {
                    String value = URLEncoder.encode(params.get(key),"UTF-8");
                    parameters  += key +"="+value+"&";
                }
                parameters  = parameters .substring(0, parameters .length()-1);
            }
            // 请求方式 是否为 GET
            boolean isGet = "get".equalsIgnoreCase(requestMethod);
            // 请求方式是否为 post
            boolean isPost = "post".equalsIgnoreCase(requestMethod);
            if(isGet){
                url += "?"+parameters;
            }
            // 创建URL 对象
            URL u = new URL(url);
            // 打来URL 链接
            HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            // 请求的参数类型(使用restlet 框架时，为了兼容框架，必须设定Content-Type为""空)
            conn.setRequestProperty("Content-Type","application/octet-stream");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置连接超时时间
            conn.setConnectTimeout(50000);
            //设置读取返回内容超时时间
            conn.setReadTimeout(50000);
            //设置向HttpURLConnetion对象中输出，因为post方式将请求参数放在http正文 ，因此需要设置
            if(isPost){
                conn.setDoOutput(true); //允许连接提交信息
            }
            //设置从HttpURLConnection对象读入，默认为true
            conn.setDoInput(true);
            // 设置是否用缓存   post 方式不能使用缓存
            if(isPost){
                conn.setUseCaches(false);
            }
            //设置请求方式，默认GET
            conn.setRequestMethod(requestMethod);
            //post 方式需要将传递的参数 输出 到 conn对象中
            // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，   2  // 所以在开发中不调用
            if(isPost){
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(parameters);
                dos.flush();
                dos.close();
            }
            // 从HttpConnection 对象中读取响应的消息
            // 执行该语句时才正式发起请求 // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
            is = conn.getInputStream();

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}
