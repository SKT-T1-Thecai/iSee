package com.cv.controller;

import com.cv.dao.trainJpa;
import com.cv.service.trainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class trainController {
    @Autowired
    trainService trainservice;

    @Autowired
    trainJpa trainjpa;

    /*查询某城市到另一城市的所有路线*/
    @ResponseBody
    @RequestMapping(value = "/whx/route/warning")
    public Map<String,Object> cityToCity(@RequestParam("start") String start, @RequestParam("dest") String dest)
    {
        List<String> tno = trainservice.cityToCity(start, dest);
        List<String> temp = new ArrayList<>(tno);
        Map<String, Object> route = new HashMap<>();
        for (String trainnumber : temp)
        {
            System.out.println(trainnumber);
            if(trainservice.cityInOrder(trainnumber,start,dest))
            {
                List<Map<String,Object>> info = trainservice.route(trainnumber);
                List<Map<String,Object>> newTemp = new ArrayList<>(info);
                route.put(trainnumber,trainservice.access(newTemp,trainnumber,start,dest));
            }
        }
        return route;
    }

    /*显示一个车次的完整路线*/
    @ResponseBody
    @RequestMapping(value = "/whx/route/complete")
    public List<Map<String,Object>> route(@RequestParam("trainnumber") String trainnumber)
    {
        return trainservice.route(trainnumber);
    }

    /*显示一个车次的精确路线*/
    @ResponseBody
    @RequestMapping(value = "/whx/route/current")
    public List<Map<String,Object>> route(@RequestParam("trainnumber") String trainnumber,@RequestParam("start") String start, @RequestParam("dest") String dest)
    {
        List<Map<String,Object>> temp = new ArrayList<>(trainservice.route(trainnumber));
        return trainservice.access(temp,trainnumber,start,dest);
    }

    /*显示两城市之间所有车次*/
    @ResponseBody
    @RequestMapping(value = "/whx/route/trainnumber")
    public List<String> trainBetweenCities(@RequestParam("start") String start, @RequestParam("dest") String dest)
    {
        if(start.equals(""))
        {
            System.out.println(1);
            List<String> result = new ArrayList<>();
            String str = "%" + dest + "%";
            for(Map<String,Object> item :trainjpa.showTrainnumberByCity(str))
            {
                System.out.println(item.get("trainnumber").toString());
                result.add(item.get("trainnumber").toString());
            }
            return result;
        }
        else if(dest.equals(""))
        {
            List<String> result = new ArrayList<>();
            String str = "%" + start + "%";
            System.out.println(2);
            for(Map<String,Object> item :trainjpa.showTrainnumberByCity(str))
            {
                result.add(item.get("trainnumber").toString());
            }
            return result;
        }
        else
        {
            System.out.println(3);
            return trainservice.cityToCity(start,dest);
        }
    }

/*    *//*显示经过某城市的所有车次*//*
    @ResponseBody
    @RequestMapping(value = "/whx/route/citytrainnumber")
    public List<String> trainBetweenCities(@RequestParam("start") String start)
    {
        List<String> result = new ArrayList<>();
        for(Map<String,Object> item :trainjpa.showTrainnumberByCity(start))
        {
            result.add(item.get("trainnumber").toString());
        }
        return result;
    }*/


    /*测试*/
    @ResponseBody
    @RequestMapping(value = "/whx/route/test")
    public List<Map<String,Object>> tttt(@RequestParam("start") String start)
    {
        String str = "%" + start + "%";
        List<Map<String,Object>> ss = trainjpa.showTrainnumberByCity(str);
        List<Map<String,Object>> temp1 = new ArrayList<>(ss);
        System.out.println(temp1);
        return temp1;
    }
}
