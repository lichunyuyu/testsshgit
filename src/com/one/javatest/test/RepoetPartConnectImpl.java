package com.one.javatest.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by vtstar on 2018/5/28.
 */
public class RepoetPartConnectImpl {

    // http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs?IDs=100014378,100014380

    /**发送Get请求
     *   url  目标地址
     *   parametersparameters 请求参数Map 类型
     *   return 远程响应结果
     * */
    public static String sendGet(String url,Map<String,String> parameters){
        String results = "";
        // 读取响应输入流
        BufferedReader in = null;
        //存储参数
        StringBuffer sb = new StringBuffer();
        // 编码之后的参数
        String params = "";
        // 编码请求参数
        try{
            if(parameters.size()==1){
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                                URLEncoder.encode(parameters.get(name),"UTF-8")
                            );
                }
                params = sb.toString();
                System.out.println("params...."+params);
            }else{
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                            URLEncoder.encode(parameters.get(name),"UTF-8")
                    ).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0,temp_params.length()-1);
                System.out.println("params...long...."+params);
            }
            String full_url = url+"?"+params;
            System.out.println("full_url....."+full_url);
            // 创建URL对象
            URL connUrl = new URL(full_url);
            //打开url 链接
            HttpURLConnection httpConn = (HttpURLConnection) connUrl.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept","*/*");
            httpConn.setRequestProperty("Connection","Keep-Alive");
            httpConn.setRequestProperty("User-Agent","Moilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String,List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有响应头字段
            for(String key:headers.keySet()){
                System.out.println("遍历所有响应头字段....."+key+"\t:\t"+headers.get(key));
            }
            //定义BufferedReader输入流来读取URL的响应，并社会种子编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            String line;
            //读取返回的内容
            while((line = in.readLine())!=null){
                results+=line;
            }

        }catch(Exception  e){
            e.printStackTrace();
        }finally {
            try{
                if(in!=null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return results;
    }

    /**  http://j2ee2009.iteye.com/blog/1481441
     * 发送post请求
     * url  目的地址
     * parameters  请求参数 Map类型
     * return 远程响应结果
     * */
    public static String sendPost(String url,Map<String,String> parameters){
        //  返回结果
        String result = "";
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 处理请求参数
        StringBuffer sb = new StringBuffer();
        //编码之后的参数
        String params = "";
        try{
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL 对象
            URL connURL = new URL(url);
            //打开 URL 连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept","*/*");
            httpConn.setRequestProperty("Connection","Keep-Alive");
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");

            //6-1  Web后端http请求(带用户名和密码防止401 Unauthorized）
            //使用代理的方式是在打开Http连接的时候同时传递一个Proxy参数。如果需要验证信息的话我们可以添加一个Http头参数来实现。
            //格式如下：
            String username = "admin";
            String password = "dev";
            //"Proxy-Authorization"= "Basic Base64.encode(user:password)"
            String headerKey = "Proxy-Authorization";
            //String headerValue = "Basic " + Base64.encode(user+":"+password);
            String headerValue = "Basic " + Base64.encode((username + ":" + password).getBytes());
            httpConn.setRequestProperty(headerKey, headerValue);
//            String auth = "Basic " + Base64.encode((username + ":" + password).getBytes());
//            httpConn.addHeader("Authorization", auth);

            //HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。
            // 可以通过以下两个语句来设置相应的超时：
            httpConn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒）
            httpConn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒）

            // 设置 POST 方式
            //设置是否从httpUrlConnection读入，默认情况下是true;
            httpConn.setDoInput(true);
            // (设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 http正文内，因此需要设为true, 默认情况下是false;
            httpConn.setDoOutput(true);
            //9  Post 请求不能使用缓存
            httpConn.setUseCaches(false);
            // 10 设定请求的方法为"POST"，默认是GET连接
            httpConn.setRequestMethod("POST");
            // 11 设定传送的内容类型是可序列化的java对象   12 (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
           // httpConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            httpConn.setRequestProperty("Content-Type",  "application/json;charset=UTF-8");// json 形式
            // 从上述第2条中url.openConnection()至此的配置必须要在connect之前完成

            //httpConn.connect();
            //获取HttpURLConnection对象对应的输出流
            // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，   2  // 所以在开发中不调用上述的connect()也可以)。
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            System.out.println("params...."+params);
            URLEncoder.encode(params,"utf-8");
            System.out.println("params2...."+params);
            out.write(params);
            //out.print(params);
            // flush 输出流的 缓冲  (必须有；这一句,以便将流信息刷入缓冲输出流.如下: )
            out.flush();
            // 定义 BufferedReader 输入流来读取URL的响应，设置编码方式
            //在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            System.out.println("in...."+in);
            String line;
            // 读取返回的内容
            while((line = in.readLine())!=null){
                result+=line;
            }

            // 断开连接
           // httpConn.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        return  result;
    }


    /**
     * 发送POST请求
     *
     * @param url
     *            目的地址
     * @param parameters
     *            请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost2(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

// 将 json 对象转换为Map  , --   嵌套多层
    public static Map<String,Object> json2Map(String jsonstr){
        Map<String,Object> map = new HashMap<>();
        if(jsonstr!=null && !"".equals(jsonstr)){
            //最外层解析
            JSONObject json = JSONObject.fromObject(jsonstr);
            for(Object k : json.keySet()){
                Object v = json.get(k);
                // 如果内层还是数组，继续解析
                if(v instanceof JSONArray){
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = it.next();
                        list.add(json2Map(json2.toString()));
                    }
                    map.put(k.toString(),list);
                }else {
                    map.put(k.toString(),v);
                }
            }
            return map;
        }else {
            return null;
        }
    }

    /**
     * 主函数 ，测试请求
     * */
    public static void main(String[] args){
        Map<String,String> parameters = new HashMap<String,String>();
//        parameters.put("name","sarin");
//        String result = sendGet("http://www.baidu.com",parameters);
//        System.out.println("resultes....."+result);
//        //  http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs?IDs=100014378,100014380
//        parameters.put("IDs","100014378,100014380");
//        sendGet("http://www.frontan.com:8081/fap/rest/api/v3/v1/trans/select/IDs",parameters);
        parameters.put("batchNumber","1117110323");  // 1117112318     1218060103
        sendGet("http://fap.wenshidata.com:8080/app/rest/v2/services/sct_VtProductLabelManagementService" +
                "/queryWorkOrderLabelInfo",parameters);

        String results = sendGet("http://fap.wenshidata.com:8080/app/rest/v2/services/sct_VtProductLabelManagementService" +
                "/queryWorkOrderLabelInfo",parameters);
        // resultes.....{"message":"系统已生产完成的制造单不存在批次号为：121806010"}

        Map<String,Object> map = json2Map(results);
        System.out.println("map....."+map);
        System.out.println("map.produceQty...."+map.get("produceQty"));
        System.out.println("map.labelInfo...."+map.get("labelInfo"));
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        list = (List<Map<String, Object>>) map.get("labelInfo");
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                Map map2 = list.get(i);
                System.out.println("map2.getcomment......"+map2.get("comment"));
            }
        }

        System.out.println("resultes....."+sendGet("http://192.168.10.62:8080/app/rest/v2/services/sct_VtProductLabelManagementService" +
                "/queryWorkOrderLabelInfo",parameters));

//
//        // 不支持请求方法'GET' java.io.IOException: Server returned HTTP response code: 415 for URL: http://localhost:8081/fap/rest/api/v3/tokens?password=dev&username=dev
//        parameters.put("username","dev");
//        parameters.put("password","dev");
//       // System.out.println("resultes....."+sendPost("http://www.frontan.com:8111/webapp/rest/api/v3/tokens",parameters));
//        String path = "http://192.168.0.101:8081/fap/rest/api/CalendarWeek/list";
//        sendPost(path,parameters);
//        //System.out.println("resultes....."+sendPost("http://localhost:8081/fap/rest/api/v3/tokens",parameters));

    }
}
