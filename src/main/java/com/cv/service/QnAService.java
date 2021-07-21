package com.cv.service;

import com.alibaba.fastjson.JSONObject;
import com.cv.dao.*;
import com.cv.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QnAService {
    @Autowired
    answerJpa answerjpa;

    @Autowired
    commentJpa commentjpa;

    @Autowired
    questionJpa questionjpa;

    @Autowired
    userJpa userjpa;

    @Autowired
    TokenRepository tokenRepository;

    /*显示问题列表*/
    public List<Map<String,Object>> QuestionList(){
        return questionjpa.questionList();
    }

    /*显示问题详情以及回答、评论*/
    public List<Map<String,Object>> QuestionDetail(int qid){
        List<Map<String,Object>> result = questionjpa.questionDetail(qid);
        List<Map<String,Object>> answers = answerjpa.answerList(qid);
        List<Map<String,Object>> temp1 = new ArrayList<>();
        List<Map<String,Object>> temp2 = new ArrayList<>();
        System.out.println("answers");
        System.out.println(answers);
        for(Map<String,Object> p : result)
        {
            Map<String,Object> q = new HashMap<>(p);
            temp1.add(q);
        }
        for(Map<String,Object> p : answers)
        {
            Map<String,Object> q = new HashMap<>(p);
            temp2.add(q);
        }
        for(Map<String,Object> answer : temp2)
        {
            List<Map<String,Object>> temp3 = new ArrayList<>();
            int aid = (int) answer.get("aid");
            List<Map<String,Object>> comments = commentjpa.commentOfAnswer(aid);
            for(Map<String,Object> p : comments)
            {
                Map<String,Object> q = new HashMap<>(p);
                temp3.add(q);
            }
            answer.put("comment",temp3);
        }
        temp1.get(0).put("answer",temp2);
        return temp1;
    }

    /*提问*/
    public JSONObject askQuestion(Map<String,Object> question){
        JSONObject res = new JSONObject();
        String tokenStr = question.get("token").toString();
        String qtitle = question.get("qtitle").toString();
        String qcontent = question.get("qcontent").toString();
        if(tokenRepository.findTokensByTokenStr(tokenStr).size()!=0){
            user user = tokenRepository.findTokensByTokenStr(tokenStr).get(0).getUser();
            questionjpa.askQuestion(user.getUid(),qtitle,qcontent);
            res.put("msg","ask question successfully.");
            res.put("state",1);
            return res;
        }
        else {
            res.put("msg", "please login first.");
            res.put("state",0);
            return res;
        }
    }

    /*回答*/
    public JSONObject answer(Map<String,Object> answer){
        JSONObject res = new JSONObject();
        String tokenStr = answer.get("token").toString();
        int qid = (int)answer.get("qid");
        if(tokenRepository.findTokensByTokenStr(tokenStr).size()!=0){
            user user = tokenRepository.findTokensByTokenStr(tokenStr).get(0).getUser();
            int priority = (int) userjpa.showAuthority(user.getUid()).get("authority");
            String acontent = answer.get("acontent").toString();
            answerjpa.addAnswer(qid,acontent,user.getUid(),priority);
            res.put("msg","answer successfully");
            res.put("state",1);
            return res;
        }
        else {
            res.put("msg", "please login first.");
            res.put("state",0);
            return res;
        }
    }

    /*评论*/
    public JSONObject comment(Map<String,Object> comment){
        JSONObject res = new JSONObject();
        String tokenStr = comment.get("token").toString();
        int aid = (int) comment.get("aid");
        String ccontent = comment.get("ccontent").toString();
        if(tokenRepository.findTokensByTokenStr(tokenStr).size()!=0){
            user user = tokenRepository.findTokensByTokenStr(tokenStr).get(0).getUser();
            commentjpa.comment(aid,ccontent,user.getUid());
            res.put("msg","comment successfully");
            res.put("state",1);
            return res;
        }
        else {
            res.put("msg", "please login first.");
            res.put("state",0);
            return res;
        }
    }

    /*搜索问题*/
    public List<Map<String,Object>> questionSearch(String keyword)
    {
        String kw = "%" + keyword + "%";
        return questionjpa.questionSearch(kw,kw);
    }
}
