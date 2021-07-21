package com.cv.dao;

import com.cv.entity.CVDataHistory;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface AnalysisRepository extends JpaRepository<CVDataHistory,Long> {

    //按照地名寻找历史信息并按照时间进行排序
    @Query(value = "SELECT date,allDeadNum,allHealNum,newConfirmNum,historyConfirmNum,nowConfirmNum FROM cvdatahistory WHERE province = ? ORDER BY date ",nativeQuery = true)
    List<Map<String,Object>> findCVDataHistoryByProvince(String province);
    //根据省份找对应城市
    @Query(value = "SELECT city,newConfirmNum,nowConfirmNum,historyConfirmNum,allDeadNum,allHealNum FROM cvdatatoday WHERE province = ? ",nativeQuery = true)
    List<Map<String,Object>> findCVDataTodayByProvince (String province);

    //全部查找
    @Query(value = "SELECT province,city,newConfirmNum,nowConfirmNum,historyConfirmNum,allDeadNum,allHealNum FROM cvdatatoday ",nativeQuery = true)
    List<Map<String,Object>> findCVDataToday();



}
