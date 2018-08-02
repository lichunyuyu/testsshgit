package com.one.javatest.test.httpconnectUrl;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vtstar on 2018/5/31.
 *
 * 功能 ： httpClient 访问远程接口工具类
 */

@SuppressWarnings("deprecation")
public class HttpClientUtil {

    /**
     * 方法体说明：向远程接口发起请求，返回字符串类型结果
     * @param url 接口地址
     * @param requestMethod 请求类型
     * @param params 传递参数
     * @return String 返回结果
     * */
    public static String httpRequestToString(String url, String requestMethod,
                                             Map<String, String> params, String ...auth){

        //接口返回结果
        String methodResult = null;
        try{
            String parameters = "";
            boolean hasParams = false;
            //将参数集合拼接成特定格式，如name=zhangsan&age=24
            if(params!=null){
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
            }

            //是否为GET方式请求
            boolean isGet = "get".equalsIgnoreCase(requestMethod);
            boolean isPost = "post".equalsIgnoreCase(requestMethod);
            boolean isPut = "put".equalsIgnoreCase(requestMethod);
            boolean isDelete = "delete".equalsIgnoreCase(requestMethod);

            //创建 HttpClient 连接对象
            DefaultHttpClient client = new DefaultHttpClient();
            HttpRequestBase method = null;  //   org.apache.http.client.methods;
            if(isGet){
                if(!parameters.equals("")){
                    url += "?"+parameters;
                }
                method = new HttpGet(url);
            }else if(isPost){
                method = new HttpPost(url);
                HttpPost postMethod = (HttpPost) method;
                StringEntity entity = new StringEntity(parameters); // org.apache.http.entity;
                postMethod.setEntity(entity);
            }else if(isPut){
                method = new HttpPut(url);
                HttpPost putMethod = (HttpPost) method;
                StringEntity entity = new StringEntity(parameters);
                putMethod.setEntity(entity);
            }else if(isDelete){
                url += "?"+parameters;
                method = new HttpDelete(url);
            }
            method.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,6000);
            //设置参数内容类型
            method.addHeader("Content-Type","application/x-www-form-urlencoded");
            //method.addHeader("Authorization","55d12c97a94444e59097e68d933dbd31");

            // httpClient 本地上下文
            HttpClientContext context = null;  //  org.apache.http.client.protocol;
            if(!(auth==null || auth.length==0)){
                String username = auth[0];
                String password = auth[1];
                UsernamePasswordCredentials credit = new UsernamePasswordCredentials(username,password); // org.apache.http.auth;
                //凭据提供器
                // org.apache.http.client;           org.apache.http.impl.client;
                CredentialsProvider provider = new BasicCredentialsProvider();
                //凭据的匹配范围
                provider.setCredentials(AuthScope.ANY,credit);
                context = HttpClientContext.create();
                context.setCredentialsProvider(provider);
            }
            //访问接口，返回状态码
            HttpResponse response = client.execute(method,context);
            //返回状态码200 ，则访问接口成功
            if(response.getStatusLine().getStatusCode()==200){
                methodResult = EntityUtils.toString(response.getEntity());
                System.out.println("methodResult....."+methodResult);
            }else{
                System.out.println("响应失败....."+methodResult);
            }
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return methodResult;
    }

    /**
     * 主函数 ，测试请求
     * */
    public static void main(String[] args){
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("name","sarin");
        String result = httpRequestToString("http://www.baidu.com","get",parameters);
        System.out.println("resultes....."+result);
        //  http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs?IDs=100014378,100014380
        parameters.put("IDs","100014378,100014380");
        httpRequestToString("http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs","get",parameters);
        System.out.println("resultes....."+httpRequestToString("http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs","get",parameters));


//        // 不支持请求方法'GET' java.io.IOException: Server returned HTTP response code: 415 for URL: http://localhost:8081/fap/rest/api/v3/tokens?password=dev&username=dev
//        parameters.put("username","dev");
//        parameters.put("password","dev");
//        String auth = "dev,dev";
//        String[] autharr = auth.split(",");
//        // System.out.println("resultes....."+sendPost("http://www.frontan.com:8111/webapp/rest/api/v3/tokens",parameters));
//        //System.out.println("resultes....."+httpRequestToString("http://192.168.0.101:8081/fap/rest/api/v3/tokens","post",parameters));
//        httpRequestToString("http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs","get",parameters,null);
//       // System.out.println("resultes....."+httpRequestToString("http://192.168.0.101:8081/fap/rest/api/v3/tokens","post",parameters));

        //httpRequestToString("http://localhost:8081/fap/rest/api/CalendarWeek/list","get",null,null);   //   加 token
    }
}
