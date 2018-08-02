package com.one.javatest.test.basicAuth;

import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vtstar on 2018/6/1.
 * HTTP基本认证(Basic Authentication)的JAVA示例
 * 大家在登录网站的时候，大部分时候是通过一个表单提交登录信息。
   但是有时候浏览器会弹出一个登录验证的对话框，如下图，这就是使用HTTP基本认证。
 * request第一次到达服务器时，服务器没有认证的信息，服务器会返回一个401 Unauthozied给客户端。
  认证之后将认证信息放在session，以后在session有效期内就不用再认证了。
 */
public class HTTPAuthServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
        String sessionAuth = (String)request.getSession().getAttribute("auth");
        if(sessionAuth!=null){
            System.out.println("this is next step");
            nextstep(request,reponse);
        }else{
            if(!checkHeaderAuth(request,reponse)){
                reponse.setStatus(401);
                reponse.setHeader("Cache-Control","no-store");
                reponse.setDateHeader("Expires",0);
                reponse.setHeader("WWW-authenticate", "Basic Realm=\"test\"");
            }
        }
    }

    private boolean checkHeaderAuth(HttpServletRequest request,HttpServletResponse reponse){
            String auth = request.getHeader("Authorization");
            System.out.println("auth encoded in base64 is"+getFromBASE64(auth));
            if((auth!=null) && (auth.length()>6)){
                auth = auth.substring(6,auth.length());
                String decodedAuth = getFromBASE64(auth);
                System.out.println("auth decoded from base64 is " + decodedAuth);
                request.getSession().setAttribute("auth",decodedAuth);
                return true;
            }else {
                return false;
            }
    }
    private String getFromBASE64(String s){
        if(s==null){
            return null;
        }
        //解码
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }

    public void nextstep(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html> next step, authentication is : " + request.getSession().getAttribute("auth") + "<br>");
        pw.println("<br></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

}

/**
 * 下面来看看一看这个认证的工作过程:
 第一步:  客户端发送http request 给服务器,服务器验证该用户是否已经登录验证过了，如果没有的话，
 服务器会返回一个401 Unauthozied给客户端，并且在Response 的 header "WWW-Authenticate" 中添加信息。
 如下图。
 第二步:浏览器在接受到401 Unauthozied后，会弹出登录验证的对话框。用户输入用户名和密码后，
 浏览器用BASE64编码后，放在Authorization header中发送给服务器。如下图:
 第三步: 服务器将Authorization header中的用户名密码取出，进行验证， 如果验证通过，将根据请求，发送资源给客户端。
 * */