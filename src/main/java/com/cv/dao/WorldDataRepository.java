package com.cv.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cv.entity.WorldData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.event.ListDataEvent;
import java.util.List;

public interface WorldDataRepository extends JpaRepository<WorldData,Integer> {
    @Query(value = "SELECT A.* FROM worlddata AS A,(SELECT MAX(date) AS MaxDate,location FROM worlddata GROUP BY location)AS B WHERE A.location = B.location AND B.MaxDate = A.date",nativeQuery = true)
    List<JSONObject> getNewest();
    List<WorldData> findByLocation(String location);
    @Query(value = " SELECT * from worlddata where location =? order by date desc limit 0,1 ;",nativeQuery = true)
    JSONObject getTodayDataByCountry(String Country);
}


