package com.cv.controller;

import com.cv.dao.DataTodayRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import com.cv.entity.CVDataToday;

import javax.transaction.Transactional;

@RestController
public class DataTodayImportController {
    @Autowired
    private DataTodayRepository dataTodayRepository;

    @SneakyThrows
    @Transactional
    @GetMapping("/importToday")
    public String importToday() {
        String strJson = null;
        try {
            strJson = Jsoup.connect("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5")
                    .ignoreContentType(true)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonValue = JSON.parseObject(strJson);
        JSONObject data = jsonValue.getJSONObject("data");
        JSONObject chinaTotal = data.getJSONObject("chinaTotal");
        JSONObject chinaAdd = data.getJSONObject("chinaAdd");
        List<JSONObject> loc = JSON.parseArray(JSON.parseObject(String.valueOf(data)).getString("areaTree"), JSONObject.class);
        List<JSONObject> locs = JSON.parseArray(JSON.parseObject(String.valueOf(loc.get(0))).getString("children"), JSONObject.class);
        CVDataToday chinaData = getChinaData(chinaTotal, chinaAdd);
        dataTodayRepository.save(chinaData);
        List<CVDataToday> provinceDataList = new ArrayList<>();
        List<CVDataToday> cityDataList = new ArrayList<>();
        for (JSONObject js : locs) {
            CVDataToday provinceData = getProvinceData(js);
            String provinceName = provinceData.getProvince();
            //dataTodayRepository.save(provinceData);
            provinceDataList.add(provinceData);
            System.out.println(js.get("name"));
            System.out.println(js.get("today"));
            System.out.println(js.get("total"));
            List<JSONObject> cities = JSON.parseArray(js.getString("children"), JSONObject.class);
            for (JSONObject city : cities) {
                System.out.println("     " + city.get("name"));
                System.out.println("     " + city.get("today"));
                System.out.println("     " + city.get("total"));
                CVDataToday cityData = getCityData(city, provinceName,provinceData);
                //dataTodayRepository.save(cityData);
                cityDataList.add(cityData);
            }
        }
        dataTodayRepository.saveAll(provinceDataList);
        dataTodayRepository.saveAll(cityDataList);

        return "Hello World!";
    }


    public CVDataToday getChinaData(JSONObject chinaTotal, JSONObject chinaAdd) {
        CVDataToday cvDataToday;
        if (!dataTodayRepository.findByLevelAndCountry(0, "中国").isEmpty())
            cvDataToday = dataTodayRepository.findByLevelAndCountry(0, "中国").get(0);
        else cvDataToday = new CVDataToday();
        cvDataToday.setCountry("中国");
        cvDataToday.setProvince("no data");
        cvDataToday.setCity("no data");
        cvDataToday.setLevel(0);


        Date date = new Date(System.currentTimeMillis());
        cvDataToday.setDate(date);
        cvDataToday.setNewSevereNum(chinaAdd.getInteger("nowSevere"));
        cvDataToday.setAllSevereNum(chinaTotal.getInteger("nowSevere"));
        cvDataToday.setAllImportedNum(chinaTotal.getInteger("importedCase"));
        cvDataToday.setNewImportedNum(chinaAdd.getInteger("importedCase"));
        cvDataToday.setNowConfirmNum(chinaTotal.getInteger("nowConfirm"));
        cvDataToday.setNewConfirmNum(chinaAdd.getInteger("confirm"));
        cvDataToday.setHistoryConfirmNum(chinaTotal.getInteger("confirm"));
        cvDataToday.setAllWzzNum(chinaTotal.getInteger("noInfect"));
        cvDataToday.setAddWzzNum(chinaAdd.getInteger("noInfect"));
        cvDataToday.setAllHealNum(chinaTotal.getInteger("heal"));
        cvDataToday.setNewHealNum(chinaAdd.getInteger("heal"));
        cvDataToday.setSuspectNum(chinaTotal.getInteger("suspect"));
        cvDataToday.setNewSuspectNum(chinaAdd.getInteger("suspect"));
        cvDataToday.setNewDeadNum(chinaAdd.getInteger("dead"));
        cvDataToday.setAllDeadNum(chinaTotal.getInteger("dead"));
        cvDataToday.setDeadRate(cvDataToday.getAllDeadNum()*1.0/cvDataToday.getHistoryConfirmNum());
        cvDataToday.setVaccineNum(null);
        return cvDataToday;
    }

    public CVDataToday getProvinceData(JSONObject provinceJS) {
        String provinceName = provinceJS.getString("name");
        CVDataToday provinceData;
        if (!dataTodayRepository.findByLevelAndProvince(1, provinceName).isEmpty())
            provinceData = dataTodayRepository.findByLevelAndProvince(1, provinceName).get(0);
        else provinceData = new CVDataToday();
        provinceData.setCountry("中国");
        JSONObject provinceTotal = provinceJS.getJSONObject("total");
        JSONObject provinceAdd = provinceJS.getJSONObject("today");

        provinceData.setProvince(provinceName);
        provinceData.setCity("no data");
        provinceData.setLevel(1);
        Date date = new Date(System.currentTimeMillis());
        provinceData.setDate(date);
        provinceData.setHealRate(provinceTotal.getDouble("healRate"));
        provinceData.setNewSevereNum(null);
        provinceData.setAllSevereNum(null);
        provinceData.setAllImportedNum(null);
        provinceData.setNewImportedNum(null);
        provinceData.setNowConfirmNum(provinceTotal.getInteger("nowConfirm"));
        provinceData.setNewConfirmNum(provinceAdd.getInteger("confirm"));
        provinceData.setHistoryConfirmNum(provinceTotal.getInteger("confirm"));
        provinceData.setAllWzzNum(provinceTotal.getInteger("wzz"));
        provinceData.setAddWzzNum(provinceAdd.getInteger("wzz_add"));
        provinceData.setAllHealNum(provinceTotal.getInteger("heal"));
        provinceData.setNewHealNum(null);
        provinceData.setSuspectNum(provinceTotal.getInteger("suspect"));
        provinceData.setNewSuspectNum(null);
        provinceData.setNewDeadNum(null);
        provinceData.setAllDeadNum(provinceTotal.getInteger("dead"));
        provinceData.setDeadRate(provinceTotal.getDouble("deadRate"));
        provinceData.setVaccineNum(null);
        return provinceData;
    }

    public CVDataToday getCityData(JSONObject cityJS, String provinceName,CVDataToday provinceData) {
        CVDataToday cityData;
        String cityName = cityJS.getString("name");
        if (!dataTodayRepository.findByLevelAndCityAndProvince(2,cityName,provinceName).isEmpty())
            cityData = dataTodayRepository.findByLevelAndCityAndProvince(2,cityName,provinceName).get(0);
        else cityData = new CVDataToday();
        cityData.setCountry("中国");
        cityData.setProvince(provinceName);
        JSONObject cityTotal = cityJS.getJSONObject("total");
        JSONObject cityAdd = cityJS.getJSONObject("today");

        cityData.setProvince(provinceName);
        cityData.setCity(cityName);
        cityData.setLevel(2);
        Date date = new Date(System.currentTimeMillis());
        cityData.setDate(date);
        cityData.setNewSevereNum(null);
        cityData.setAllSevereNum(null);
        cityData.setAllImportedNum(null);
        cityData.setNewImportedNum(null);
        cityData.setNowConfirmNum(cityTotal.getInteger("nowConfirm"));
        cityData.setNewConfirmNum(cityAdd.getInteger("confirm"));
        cityData.setHistoryConfirmNum(cityTotal.getInteger("confirm"));
        cityData.setAllWzzNum(cityTotal.getInteger("wzz"));
        cityData.setAddWzzNum(null);
        cityData.setAllHealNum(cityTotal.getInteger("heal"));
        cityData.setNewHealNum(null);
        cityData.setSuspectNum(cityTotal.getInteger("suspect"));
        cityData.setNewSuspectNum(null);
        cityData.setNewDeadNum(null);
        cityData.setHealRate(cityTotal.getDouble("healRate"));
        cityData.setAllDeadNum(cityTotal.getInteger("dead"));
        cityData.setDeadRate(cityTotal.getDouble("deadRate"));
        cityData.setVaccineNum(null);
        if(cityData.getCity().equals("境外输入"))
        {
            provinceData.setAllImportedNum(cityData.getNowConfirmNum());
            provinceData.setNewImportedNum(cityData.getNewConfirmNum());
        }
        return cityData;

    }


}
