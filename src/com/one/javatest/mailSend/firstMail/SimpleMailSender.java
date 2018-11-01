package com.one.javatest.mailSend.firstMail;

/**
 * Created by vtstar on 2018/10/22.
 */

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 *  2. SimpleMailSender 实现发送邮件的功能
 *  简单邮件发送器
 * */
public class SimpleMailSender {

    /**
     * 以文本格式发送邮件
     * @param mailInfo
     * */
    public boolean sendTextMail(MailSenderInfo mailInfo){
        // 判断是否需要身份验证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if(mailInfo.isValidate()){
            // 如果需要身份验证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
        }
        // 根据邮件会话属性 和 密码验证器构造一个 发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
        try{
            // 根据 session 创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址 ，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO,to);
            //设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            //设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            //发送邮件
            Transport.send(mailMessage);
            return true;

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 以HTML格式发送邮件
     * @param mailInfo 待发送的邮件消息
     * */
    public boolean sendHtmlMail(MailSenderInfo mailInfo){
        // 判断是否需要身份验证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if(mailInfo.isValidate()){
            authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
        }
        // 根据邮件会话属性 和 密码验证器构造一个发送邮件的 session
        Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
        try{
            //根据Session 创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            //创建邮件发送者的地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 设置邮件接收者的地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO,to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart 类是一个容器类，包含MimeBodyPart类型的对象
            Multipart multipart = new MimeMultipart();
            //创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML 内容
            html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
            multipart.addBodyPart(html);
            //将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(multipart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** 以HTML 格式 发送邮件
     * 并且添加附件格式
     * @param mailInfo 待发送的邮件消息
     * */
    public boolean sendAttachMail(MailSenderInfo mailInfo){
        // 判断是否需要身份验证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if(mailInfo.isValidate()){
            // 如果 需要身份验证
            authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的Session
        Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
       try{
           // 根据Session 创建一个邮件消息
           Message mailMessage = new MimeMessage(sendMailSession);
           // 创建邮件发送者地址
           Address from = new InternetAddress(mailInfo.getFromAddress());
           // 设置邮件消息的发送者
           mailMessage.setFrom(from);
           // 创建邮件的接收者地址 ，并设置到邮件消息中
           Address to = new InternetAddress(mailInfo.getToAddress());
           mailMessage.setRecipient(Message.RecipientType.TO,to);
           //设置邮件消息的主题
           mailMessage.setSubject(mailInfo.getSubject());
           //设置邮件消息发送的时间
           mailMessage.setSentDate(new Date());
           //设置待附件的格式
           Multipart multipart = new MimeMultipart();
           //设置正文
           MimeBodyPart textBodyPart = new MimeBodyPart();
           textBodyPart.setText(mailInfo.getContent());
           multipart.addBodyPart(textBodyPart);
           //设置附件
           MimeBodyPart attrBodyPart = new MimeBodyPart();
           DataSource dataSource = new FileDataSource(new File("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\mailSend\\resources\\test.txt"));
           multipart.addBodyPart(attrBodyPart);
           mailMessage.setContent(multipart,"text/html;charset=utf-8");
           //发送邮件
           Transport.send(mailMessage);
           return true;
       } catch (AddressException e) {
           e.printStackTrace();
       } catch (MessagingException e) {
           e.printStackTrace();
       }
       return false;
    }


}
