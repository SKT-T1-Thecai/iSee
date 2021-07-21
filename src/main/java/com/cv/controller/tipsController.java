package com.cv.controller;

import com.cv.dao.tipsJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class tipsController {
    @Autowired
    tipsJpa tipsjpa;

    /*发布小知识*/
    @RequestMapping(value = "/whx/tips/release")
    public void addTips(@RequestBody Map<String,Object> tips )
    {
        String tcontent = tips.get("tcontent").toString();
        String title = tips.get("ttitle").toString();
        tipsjpa.addTips(title,tcontent);
    }

    /*显示小知识列表*/
    @ResponseBody
    @RequestMapping(value = "/whx/tips/list")
    public List<Map<String,Object>> showTips()
    {
        return tipsjpa.showTips();
    }

    /*显示小知识内容*/
    @ResponseBody
    @RequestMapping(value = "/whx/tips/detail")
    public List<Map<String,Object>> tipsDetail(@RequestParam("tid") int tid){
        return tipsjpa.tipsDetail(tid);
    }

    /*删除小知识*/
    @RequestMapping(value = "/whx/tips/delete")
    public void deleteTips(@RequestParam("tid")int tid){
        tipsjpa.deleteTips(tid);
    }
}
