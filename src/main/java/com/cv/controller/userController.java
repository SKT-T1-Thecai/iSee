package com.cv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cv.dao.TokenRepository;
import com.cv.dao.userJpa;
import com.cv.entity.user;
import com.cv.service.EmailService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class userController {
    @Autowired
    userJpa userjpa;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    EmailService emailService;

    /*展示个人信息*/
    @ResponseBody
    @RequestMapping(value = "/whx/user/info")
    public Map<String,Object> userInfo(@RequestParam("token") String token)
    {
        Map<String,Object> res = new HashMap<>();
        if(tokenRepository.findTokensByTokenStr(token).size()!=0) {
            user user = tokenRepository.findTokensByTokenStr(token).get(0).getUser();
            int uid = user.getUid();
            return userjpa.showInfo(uid);
        }
        else {
            res.put("msg", "please login first.");
            res.put("state",0);
            return res;
        }
    }

    /*修改个人信息*/
    @RequestMapping(value = "/whx/user/info/modify")
    public Map<String,Object> modifyInfo(@RequestBody Map<String, Object>user)
    {
        Map<String,Object> res = new HashMap<>();
        String token = user.get("token").toString();
        if(tokenRepository.findTokensByTokenStr(token).size()!=0) {
            user u = tokenRepository.findTokensByTokenStr(token).get(0).getUser();
            int uid = u.getUid();
            String uname = user.get("uname").toString();
            String email = user.get("email").toString();
/*            String pwd = user.get("pwd").toString();*/
            userjpa.modifyInfo(uname,email,uid);
            res.put("msg", "success.");
            res.put("state",1);
            return res;
        }
        else
        {
            res.put("msg", "please login first.");
            res.put("state",0);
            return res;
        }
    }

    /*权威认证*/
    @RequestMapping(value = "/whx/user/authorization")
    public Map<String,Object> authorization(@RequestBody Map<String,Object> info)
    {
        Map<String,Object> res = new HashMap<>();
        String token = info.get("token").toString();
        if(tokenRepository.findTokensByTokenStr(token).size()!=0) {
            user u = tokenRepository.findTokensByTokenStr(token).get(0).getUser();
            int uid = u.getUid();
            String email = u.getEmail();
            if(email.equals(info.get("email").toString()))
            {
                userjpa.authorization(uid);
                res.put("msg", "success.");
                res.put("state",1);
                return res;
            }
            else
            {
                res.put("msg", "fail.");
                res.put("state",0);
                return res;
            }
        }
        else
        {
            {
                res.put("msg", "please login first.");
                res.put("state",0);
                return res;
            }
        }
    }

    @ResponseBody
    @RequestMapping("/send")
    public Map<String, Object> send(@RequestParam("email") String email){
        return emailService.send(email);
    }

    @ResponseBody
    @GetMapping("/locate")
    public JSONObject getLocation(@RequestParam("ip") String ip){
        String url = "https://restapi.amap.com/v5/ip?key=f2fcfead9f23d8001b29195fa4756ffc&type=4&ip=" + ip;
        String strJson = null;
        try {
            strJson = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonValue = JSON.parseObject(strJson);
        return jsonValue;
    }
}
