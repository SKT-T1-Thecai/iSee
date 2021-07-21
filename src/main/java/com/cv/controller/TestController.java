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
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private WHODataBase WHODataBase;
    @SneakyThrows
    @Transactional
    @GetMapping("/Test")
    public List<JSONObject> Test(){
        List<JSONObject> list = WHODataBase.getNewestDataOfEachCountry();
        return list;
    }

}
