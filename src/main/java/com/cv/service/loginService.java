package com.cv.service;

import com.alibaba.fastjson.JSONObject;
import com.cv.dao.TokenRepository;
import com.cv.dao.userJpa;
import com.cv.entity.Token;
import com.cv.entity.user;
import com.cv.utils.GenerateRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class loginService {
    @Autowired
    userJpa userjpa;
    @Autowired
    TokenRepository tokenRepository;

    /*登录*/
    public Map<String,Object> login(Map<String,Object> info, HttpServletRequest request)
    {
        Map<String,Object> res = new HashMap<>();
        String email = info.get("email").toString();
        String password = info.get("pwd").toString();
        user user = userjpa.findByEmailAndPwd(email,password);
        if(user!=null){
            String newTokenStr = GenerateRandomString.generateString(24);
            Token token = tokenRepository.findByUser(user)!=null?
                    tokenRepository.findByUser(user):new Token();
            token.setUser(user);
            token.setTokenStr(newTokenStr);
            tokenRepository.save(token);
            res.put("msg","login success");
            res.put("state",1);
            res.put("token",newTokenStr);
            res.put("uid",user.getUid());
            res.put("username",user.getUname());
            res.put("userEmail",user.getEmail());
            res.put("authority",user.getAuthority());
            return res;
        }else {
            res.put("msg","your email or password is wrong ,please check again");
            res.put("state",0);
            return res;
        }
    }

    /*注册*/
    public JSONObject register(Map<String,Object> info)
    {
        String name = info.get("uname").toString();
        String email = info.get("email").toString();
        String pwd = info.get("pwd").toString();
        JSONObject res = new JSONObject();
        if(userjpa.emailCheck(email).get("uid")!=null)//邮箱已被注册
        { res.put("msg","your email has been used.");
          res.put("state",0);
          return res;
        }
        userjpa.register(name,email,pwd);
        res.put("msg","register successfully.");
        res.put("state",1);
        return res;
    }
}
