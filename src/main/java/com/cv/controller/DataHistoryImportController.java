package com.cv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cv.dao.DataHistoryRepository;
import com.cv.dao.DataTodayRepository;
import com.cv.entity.CVDataHistory;
import com.cv.utils.DateUtils;
import lombok.Data;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class DataHistoryImportController {
    @Autowired
    private DataTodayRepository dataTodayRepository;
    @Autowired
    private DataHistoryRepository dataHistoryRepository;

    private static String[] provinces = {
            "河北","山西","辽宁","吉林","黑龙江","江苏",
            "浙江","安徽","福建","江西","山东","河南",
            "湖北","湖南","广东","海南","四川","贵州",
            "云南","陕西","甘肃","青海","台湾","北京",
            "上海","天津","重庆","香港","澳门","内蒙古",
            "广西","宁夏","新疆","西藏"};
    @SneakyThrows
    @Transactional
    @GetMapping("/importHistory")
    public String importHistory() throws ParseException {
        String strJson = null;
        List<CVDataHistory> cvDataList = new ArrayList<>();
        for(String pName:provinces)
        {
            try {
            strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/query/pubished/daily/list?province="
                    +pName)
                    .ignoreContentType(true)
                    .execute()
                    .body();
                } catch (IOException e) {
            e.printStackTrace();
                }
            JSONObject jsonValue = JSON.parseObject(strJson);
            String data = jsonValue.getString("data");
            List<JSONObject> dataList = JSON.parseArray(data, JSONObject.class);

            int length = dataList.size();
            System.out.println(length);
            int flag = 0;
            for(int i=length-1;i>=0;i--)
            {
                if(flag==1)
                {
                    flag = 0;
                    continue;
                }
                JSONObject hisData = dataList.get(i);
                CVDataHistory cvDataHistory = getCVDataHistory(hisData,pName);
                if(cvDataHistory!=null)
                {
                    if(pName.equals("湖北")&&DateUtils.DateToString(cvDataHistory.getDate())
                    .equals("2021-02-03"))
                        flag =1;
                    cvDataList.add(cvDataHistory);}
                else break;
            }
            }
        dataHistoryRepository.saveAll(cvDataList);
        //generateChinaHistory();
        return "success";
    }
    public CVDataHistory getCVDataHistory(JSONObject data,String province) throws ParseException {
        CVDataHistory cvDataHistory;
        String dateStr = data.getInteger("year").toString()+"-"+
                data.getString("date").replace(".","-");
        if(!dataHistoryRepository.findCVDataHistoriesByDateAndProvince(dateStr,
                data.getString("province")).isEmpty()){
            return null;
        }
        else
            cvDataHistory = new CVDataHistory();
        cvDataHistory.setCountry("中国");
        cvDataHistory.setLevel(1);
        cvDataHistory.setCity("no data");
        cvDataHistory.setAllDeadNum(data.getInteger("dead"));
        cvDataHistory.setHistoryConfirmNum(data.getInteger("confirm"));
        cvDataHistory.setNowConfirmNum(data.getInteger("confirm")-data.getInteger("heal")-data.getInteger("dead"));
        cvDataHistory.setNewConfirmNum(data.getInteger("newConfirm"));
        cvDataHistory.setAllWzzNum(data.getInteger("wzz"));
        cvDataHistory.setNewWzzNum(data.getInteger("wzz_add"));
        cvDataHistory.setAllHealNum(data.getInteger("heal"));
        cvDataHistory.setNewHealNum(data.getInteger("newHeal"));
        cvDataHistory.setAllDeadNum(data.getInteger("dead"));
        cvDataHistory.setNewDeadNum(data.getInteger("newDead"));
        cvDataHistory.setSuspectNum(null);
        cvDataHistory.setNewImportedNum(null);
        cvDataHistory.setAllImportedNum(null);
        cvDataHistory.setVaccineNum(null);
        cvDataHistory.setNowConfirmNum(cvDataHistory.getHistoryConfirmNum()-cvDataHistory.getAllHealNum()
        -cvDataHistory.getAllDeadNum());
        cvDataHistory.setHealRate((cvDataHistory
                .getAllHealNum()+cvDataHistory.getAllDeadNum())!=0?cvDataHistory.getAllHealNum()*1.0/(cvDataHistory
        .getAllHealNum()+cvDataHistory.getAllDeadNum()):0);
        cvDataHistory.setDeadRate(cvDataHistory.getHistoryConfirmNum()!=0?
                cvDataHistory.getAllDeadNum()*1.0/cvDataHistory.getHistoryConfirmNum():0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        cvDataHistory.setDate(date);
        cvDataHistory.setProvince(province);
        return cvDataHistory;
    }
    @GetMapping("/generate")
    public void generateChinaHistory(){
        Date d = DateUtils.getToday();
        d=DateUtils.getLastDay(d);
        List<CVDataHistory> saveList = new ArrayList<>();
       while(true)
       {

           String dateStr = DateUtils.DateToString(d);
           if (!dataHistoryRepository.findCVDataHistoriesByDateStrAndLevel(dateStr,0).isEmpty())
           break;
           List<CVDataHistory> cvDataHistoryList = dataHistoryRepository.findCVDataHistoriesByDateStr(DateUtils.DateToString(d));
           if(cvDataHistoryList.size()<20)
               break;
           System.out.println(cvDataHistoryList.size());
           CVDataHistory cvDataHistory = new CVDataHistory();
           cvDataHistory.setCountry("中国");
           cvDataHistory.setLevel(0);
           cvDataHistory.setCity("no data");
           cvDataHistory.setProvince("no data");
           cvDataHistory.setDate(d);
           int allDead =0;
           int newDead =0;
           int nowConfirm = 0;
           int historyConfirm = 0;
           int newConfirm = 0;
           int allHeal=0;
           int newHeal=0;
           int wzz=0;
           int wzz_add=0;
           for(CVDataHistory cvDataHistory1:cvDataHistoryList)
           {
               allDead+= cvDataHistory1.getAllDeadNum();
               newDead+= cvDataHistory1.getNewDeadNum();
               historyConfirm+= cvDataHistory1.getHistoryConfirmNum();
               newConfirm+= cvDataHistory1.getNewConfirmNum();
               allHeal+= cvDataHistory1.getAllHealNum();
               newHeal+=cvDataHistory1.getNewHealNum();
               wzz+= cvDataHistory1.getAllWzzNum();
               wzz_add+= cvDataHistory1.getNewWzzNum();
               nowConfirm+=cvDataHistory1.getNowConfirmNum();
           }
           cvDataHistory.setNowConfirmNum(nowConfirm);
           cvDataHistory.setAllDeadNum(allDead);
           cvDataHistory.setNewDeadNum(newDead);
           cvDataHistory.setNewConfirmNum(newConfirm);
           cvDataHistory.setHistoryConfirmNum(historyConfirm);
           cvDataHistory.setNewConfirmNum(newConfirm);
           cvDataHistory.setNewHealNum(newHeal);
           cvDataHistory.setAllHealNum(allHeal);
           cvDataHistory.setNewWzzNum(wzz_add);
           cvDataHistory.setAllWzzNum(wzz);
           saveList.add(cvDataHistory);
           d = DateUtils.getLastDay(d);
       }
       dataHistoryRepository.saveAll(saveList);


    }

}
