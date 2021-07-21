package com.cv.controller;

import com.cv.dao.cityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class cityController {
    @Autowired
    cityJpa cityjpa;

    @ResponseBody
    @RequestMapping(value = "/whx/city/search1")
    public List<Map<String,Object>> citySearch1(@RequestParam("city") String city){
        String key = "%" + city + "%";
        return cityjpa.cityLocate(key);
    }

    @ResponseBody
    @RequestMapping(value = "/whx/city/search2")
    public List<Map<String,Object>> citySearch2(@RequestParam("province") String province){
        String key = "%" + province + "%";
        return cityjpa.cityOfProvince(key);
    }
}
