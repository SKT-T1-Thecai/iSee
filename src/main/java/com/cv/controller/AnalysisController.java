package com.cv.controller;

import com.cv.service.AnalysisService;
import org.json.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;


    @ResponseBody
    @RequestMapping("/history")
    public List<Map<String,Object>> setByCountry (@RequestParam String Province){
        return analysisService.getCvDataHistory(Province);
    }

    @ResponseBody
    @RequestMapping("/chinaHistory")
    public JSONObject getChinaHistory() throws JSONException {
        return analysisService.getChinaHistory();
    }

    @ResponseBody
    @RequestMapping("/provincelist")
    public List<Map<String, Object>> getList(@RequestParam String Province){
        return analysisService.getCvDataTodayByProvince(Province);
    }

    @ResponseBody
    @RequestMapping("list")
    public List<Map<String, Object>> getList(){
        return analysisService.getCvDataToday();
    }
}

