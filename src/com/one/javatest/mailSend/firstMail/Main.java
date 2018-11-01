package com.one.javatest.mailSend.firstMail;

/**
 * Created by vtstar on 2018/10/22.
 */

/**
 * 4. 用作测试使用
 * */
public class Main {
    public static void main(String[] args) {
        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        //服务器端口
        //mailInfo.setMailServerHost("smtp.126.com");
        // 或者通过 qq邮箱发送
        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 您的邮箱用户名
        mailInfo.setUserName("760254157@qq.com");
        //您的邮箱密码
        mailInfo.setPassword("wckqxiwjatcxbdbb");
        //发送邮件源地址
        mailInfo.setFromAddress("760254157@qq.com");
        //发送邮件目的地址
        mailInfo.setToAddress("1123130476@qq.com");
        //主题
        mailInfo.setSubject("设置邮箱标题如：琰琰是个大坏蛋");
        //内容
        mailInfo.setContent("设置邮箱内容如：情不知何起，一往而深。" +
                "附提莫 https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1540199204&di=360a8c732ea04392cd13e3affcc9ee86&src=http://img3.duitang.com/uploads/item/201605/12/20160512091604_ZkJUa.jpeg");
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        //sms.sendTextMail(mailInfo); // 发送文本格式
        sms.sendHtmlMail(mailInfo);//发送Html格式
        //sms.sendAttachMail(mailInfo);//发送带附件格式

    }
}
