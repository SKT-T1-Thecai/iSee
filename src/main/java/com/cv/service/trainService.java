
package com.cv.service;


import com.cv.dao.trainJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class trainService {
    @Autowired
    trainJpa trainjpa;

    /*查询从某城市到另一城市的所有高铁车次*/
    public List<String> cityToCity(String start, String destination)
    {
        //得到该城市的所有高铁站
        String str1 = "%" + start + "%";
        String str2 = "%" + destination + "%";
        List<String> result = new ArrayList<>();
        List<Map<String,Object>> trains1 = trainjpa.showTrainnumberByCity(str1);
        List<Map<String,Object>> trains2 = trainjpa.showTrainnumberByCity(str2);
        if(trains1.size()==0 ||trains2.size()==0)
        {
            result.add("city not found.");
            return result;
        }
        else if(trains1.get(0).get("city").equals(trains2.get(0).get("city")))
        {
            result.add("same city.");
            return result;
        }
        result.add("success.");
        List<String> temp1 = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        for(Map<String, Object> item : trains1)
        {
            temp1.add(item.get("trainnumber").toString());
        }
        for(Map<String, Object> item : trains2)
        {
            temp2.add(item.get("trainnumber").toString());
        }
        temp1.retainAll(temp2);
        Iterator<String> iterator = temp1.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (!cityInOrder(item,start,destination)) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        for(String item : temp1)
        {
            if(!result.contains(item))
                result.add(item);
        }
        return result;
    }

    /*判断某车次上某城市是否在另一城市之后*/
    public Boolean cityInOrder(String trainnumber, String start, String destination)
    {
        String str1 = "%" + start + "%";
        String str2 = "%" + destination + "%";
        int order1 = (int) trainjpa.showCityOrder(str1,trainnumber).get(0).get("sorder");
        int order2 = (int) trainjpa.showCityOrder(str2,trainnumber).get(0).get("sorder");
        return order1 < order2;
    }

    /*查询某车次所有经停站*/
    public List<Map<String,Object>> route(String trainnumber)
    {
        return trainjpa.showRouteByTrainnumber(trainnumber);
    }

    /*删除出发地和目的地之外的站点*/
    public List<Map<String,Object>> access(List<Map<String,Object>> temp,String trainnumber,String start,String dest)
    {
        int order1 = orderOfCity(trainnumber,start);
        int order2 = orderOfCity(trainnumber,dest);
        Iterator<Map<String,Object>> iterator = temp.iterator();
        while (iterator.hasNext()) {
            Map<String,Object> newItem = iterator.next();
            if ((((int)newItem.get("sorder")) < order1)  || (((int)newItem.get("sorder")) > order2)) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        return temp;
    }

    /*取得某城市所在车次的站次*/
    public int orderOfCity(String trainnumber, String city)
    {
        String str1 = "%" + city + "%";
        return (int) trainjpa.showCityOrder(str1,trainnumber).get(0).get("sorder");
    }
}
