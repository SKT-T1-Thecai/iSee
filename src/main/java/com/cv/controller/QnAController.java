package com.cv.controller;

import com.alibaba.fastjson.JSONObject;
import com.cv.service.QnAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class QnAController {
    @Autowired
    QnAService QnAservice;

    /*搜索问题*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/search")
    public List<Map<String,Object>> QuestionSearch(@RequestParam("keyword") String keyword){
        return QnAservice.questionSearch(keyword);
    }

    /*显示问题列表*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/list")
    public List<Map<String,Object>> QuestionList(){
        return QnAservice.QuestionList();
    }

    /*显示问题页面具体内容*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/detail")
    public List<Map<String,Object>> questionDetail(@RequestParam("qid") int qid)
    {
        return QnAservice.QuestionDetail(qid);
    }

    /*提问*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/ask")
    public JSONObject askQuestion(@RequestBody Map<String,Object> question)
    {
        return QnAservice.askQuestion(question/*,request*/);
    }

    /*回答*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/answer")
    public JSONObject answerQuestion(@RequestBody Map<String,Object> answer)
    {
        return QnAservice.answer(answer);
    }

    /*评论*/
    @ResponseBody
    @RequestMapping(value = "/whx/question/comment")
    public JSONObject comment(@RequestBody Map<String,Object> comment)
    {
        return QnAservice.comment(comment);
    }
}

