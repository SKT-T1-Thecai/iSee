package com.cv.dao;

import com.alibaba.fastjson.JSONObject;
import com.cv.entity.WHODataCase;
import com.cv.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@Repository
public interface WHODataBase extends JpaRepository<comment,Integer> {
    /*获取每个国家最新疫情信息*/
    @Query(value = "SELECT A.* FROM whodatacase AS A,(SELECT MAX(Date_reported) AS MaxDate,Country,Date_reported FROM whodatacase GROUP BY Country)AS B WHERE A.Country = B.Country AND B.MaxDate = A.Date_reported",nativeQuery = true)
    List<JSONObject> getNewestDataOfEachCountry();

    @Query(value = "SELECT * FROM whodatacase WHERE Country = ?", nativeQuery = true)
    List<JSONObject> findWHODataCaseByCountry(String country);
}
