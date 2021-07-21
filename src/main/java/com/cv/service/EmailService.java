package com.cv.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    JavaMailSenderImpl mailSender;

    private String emailServiceCode;
    public Map<String,Object> send(String emailTo){
        Map<String,Object> res = new HashMap<>();
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        emailServiceCode = str.toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setText("验证码是：" + emailServiceCode);
        message.setFrom("17855776325@163.com");
        message.setTo(emailTo);
        try{
            mailSender.send(message);
            //base64简单加密
            String base64 = Base64.encodeBase64URLSafeString(emailServiceCode.getBytes());
            res.put("code",base64);
            res.put("msg","send success.");
            res.put("statu",1);
        }catch (Exception e)
        {
            res.put("msg","email not found.");
            res.put("statu",0);
        }
/*        mailSender.send(message);
        //base64简单加密
        String base64 = Base64.encodeBase64URLSafeString(emailServiceCode.getBytes());
        res.put("code",base64);
        res.put("msg","send success.");
        res.put("statu",1);*/
        return res;
    }
}
