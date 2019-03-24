package com.superficial.img.common.tool;


import java.util.Properties;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTool {
    private EmailTool(){

    }

    public   static  Boolean  sendMessage(String title,String msgStr) throws MessagingException {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "465");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);
        msg.setSubject(title);
        msg.setText(msgStr);
        msg.setFrom(new InternetAddress("superficial_one@163.com"));
        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", "superficial_one@163.com", "wozaizheli1995");
        transport.sendMessage(msg, new Address[] { new InternetAddress("1249581758@qq.com") });
        transport.close();
        return  false;
    }
}
