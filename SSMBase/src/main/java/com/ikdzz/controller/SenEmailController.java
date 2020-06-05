package com.ikdzz.controller;

import com.ikdzz.service.Impl.MailSenderSrvServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/email")
public class SenEmailController {

    @Autowired
    private MailSenderSrvServices mSender;

    //定时器注解

    @RequestMapping("/send")
    public String senMsg(){
        String to = "1501376733@qq.com";  //收件人地址
        String subject = "这是一封新的邮件";   //邮件标题
        String content = "爱你呦，么么哒，褚头三！";    //邮件内容
        mSender.sendEmail(to,subject,content);    //发送邮件
        return  "success";
    }




}
