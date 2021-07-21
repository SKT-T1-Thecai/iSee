package com.cv.service;

import com.alibaba.fastjson.JSON;
import com.cv.dao.AnalysisRepository;
import com.cv.dao.DataHistoryRepository;
import com.cv.entity.CVDataHistory;
import com.cv.utils.DateUtils;
import lombok.SneakyThrows;
import org.json.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;


@Service
public class AnalysisService {

    @Autowired
    AnalysisRepository analysisRepository;
    @Autowired
    DataHistoryRepository dataHistoryRepository;


     public List<Map<String,Object>> getCvDataHistory(String Province){
         return analysisRepository.findCVDataHistoryByProvince(Province);
     }
     public JSONObject getChinaHistory() throws JSONException {
         JSONObject res = new JSONObject();
         List<CVDataHistory> histories = dataHistoryRepository.
                 findCVDataHistoriesByCountryAndLevel("中国",0);
         List<JSONObject> dataList = new ArrayList<>();
         for (CVDataHistory cvDataHistory:histories)
         {
             JSONObject unit = new JSONObject();
             unit.put("date", DateUtils.DateToString(cvDataHistory.getDate()));
             unit.put("allDeadNum",cvDataHistory.getAllDeadNum());
             unit.put("allHealNum",cvDataHistory.getAllHealNum());
             unit.put("newConfirmNum",cvDataHistory.getNewConfirmNum());
             unit.put("historyConfirm",cvDataHistory.getHistoryConfirmNum());
             unit.put("nowConfirm",cvDataHistory.getNowConfirmNum());
             dataList.add(unit);
         }
         Collections.sort(dataList,new Comparator<JSONObject>() {
             @SneakyThrows
             @Override
             public int compare(JSONObject o1, JSONObject o2) {
                 try {
                     return DateUtils.StringToDate(o1.getString("date")).getTime()<
                             (DateUtils.StringToDate(o2.getString("date")).getTime()) ? -1: 0;
                 } catch (ParseException e) {
                     e.printStackTrace();
                     return 1;
                 }
             }
         });
         res.put("msg","success");
         res.put("data",dataList);
         return res;
     }
    public List<Map<String,Object>> getCvDataTodayByProvince(String Province){
        return analysisRepository.findCVDataTodayByProvince(Province);
    }

    public List<Map<String,Object>> getCvDataToday(){
        return analysisRepository.findCVDataToday();
    }

}
