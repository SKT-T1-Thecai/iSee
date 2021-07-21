package com.cv.controller;

import com.alibaba.fastjson.JSONObject;
import com.cv.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class loginController {
    @Autowired
    loginService loginservice;

    /*登录*/
    @ResponseBody
    @RequestMapping(value = "/whx/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> user, HttpServletRequest request){
        return loginservice.login(user,request);
    }

    /*注册*/
    @ResponseBody
    @RequestMapping(value = "/whx/register")
    public JSONObject register(@RequestBody Map<String,Object> user)
    {
       return loginservice.register(user);
    }
}
