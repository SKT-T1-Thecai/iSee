package com.cv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cv.dao.DataHistoryRepository;
import com.cv.dao.DataTodayRepository;
import com.cv.dao.WHODataBase;
import com.cv.entity.WHODataCase;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class InternationalNewestDataController {
    @Autowired
    private WHODataBase WHODataBase;
    @SneakyThrows
//    @Transactional
    /*获取国际最新数据*/
    @GetMapping("/International_newest_who")
    public JSONObject International(){
        JSONObject res = new JSONObject();
        res.put("msg","success");

        List<JSONObject> list = WHODataBase.getNewestDataOfEachCountry();
        res.put("data",list);
        return res;
    }

    @GetMapping("/history_who")
    public JSONObject history(@RequestParam String country){
        JSONObject res = new JSONObject();
        res.put("msg","success");
        List<JSONObject> list = WHODataBase.findWHODataCaseByCountry(country);
        res.put("data",list);
        return res;
    }
}
