package com.one.javatest.mailSend.firstMail;

/**
 * Created by vtstar on 2018/10/22.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 3. MyAuthenticator 主要实现邮箱的认证
 * */
public class MyAuthenticator extends Authenticator {
    String userName = null;
    String passWord = null;

    public MyAuthenticator(){

    }

    public MyAuthenticator(String username,String password){
        this.userName = username;
        this.passWord = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName,passWord);
    }
}
