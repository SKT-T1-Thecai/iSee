package com.cv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cv.dao.DataTodayRepository;
import com.cv.dao.WHODataVaccineRepository;
import com.cv.entity.CVDataToday;
import com.cv.entity.WHODataVaccine;
import com.cv.utils.VaccineUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/cvdata")
@RestController
public class CVDataSearchController {
    @Autowired
    private DataTodayRepository dataTodayRepository;
    @Autowired
    private WHODataVaccineRepository whoDataVaccineRepository;
    @GetMapping("/chinaMain")
    public JSONObject getChinaMainData(){
        CVDataToday chinaData = dataTodayRepository.findByLevelAndCountry(0,"中国").get(0);
        return JSONObject.parseObject(JSONObject.toJSON(chinaData).toString());
    }

    /**
     *
     * @param province
     * @return
     */
    @GetMapping("/province")
    public JSONObject getProvinceData(String province){
        CVDataToday provinceData = dataTodayRepository.findByLevelAndProvince(1,province).get(0);
        JSONObject jo = JSONObject.parseObject(JSONObject.toJSON(provinceData).toString());
        jo.put("vaccineRate", VaccineUtils.getVaccineNum(province));
        return jo;
    }
    public String qxg(String s) {
        return s.replace("\\","").replace("\"{","{").replace(
                "}\"","}"
        );
    }

    @GetMapping("/city")
    public JSONObject getCityData(String city){
        CVDataToday cityData = dataTodayRepository.findByLevelAndCity(2,city).get(0);
        return JSONObject.parseObject(JSONObject.toJSON(cityData).toString());
    }
    @GetMapping("/checkjson")
    public JSONObject getForeignJson(){
        String strJson = null;
        try {
            strJson = Jsoup.connect("https://view.inews.qq.com/g2/getOnsInfo?name=disease_other")
                    .ignoreContentType(true)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonValue = JSON.parseObject(qxg(strJson));
        return jsonValue;
    }
    @GetMapping("/allProvinceNowConfirm")
    public JSONObject getAllProvinceNowConfirm(){
        List<CVDataToday> datalist = dataTodayRepository.findByLevelAndCountry(1,"中国");
        JSONObject res = new JSONObject();
        List<JSONObject> provinceList = new ArrayList<>();
        for(CVDataToday cvDataToday:datalist)
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",cvDataToday.getProvince());
            jsonObject.put("nowConfirm",cvDataToday.getNowConfirmNum());
            provinceList.add(jsonObject);
        }
        res.put("msg","success");
        res.put("data",provinceList);
        return res;
    }
    @GetMapping("/allCityInProvince")
    public JSONObject getAllCityInProvince(String province){
        List<CVDataToday> datalist = dataTodayRepository.findByLevelAndProvince(2,province);
        JSONObject res = new JSONObject();
        List<JSONObject> cityList = new ArrayList<>();
        for(CVDataToday cvDataToday:datalist)
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",cvDataToday.getCity());
            jsonObject.put("nowConfirm",cvDataToday.getNowConfirmNum());
            cityList.add(jsonObject);
        }
        res.put("msg","success");
        res.put("data",cityList);
        return res;

    }
    @GetMapping("/getTencentWorldData")
    public JSONObject getTencentWorldData() throws IOException {
        String strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/automation/foreign/country/ranklist")
                .ignoreContentType(true)
                .execute()
                .body();
        return JSON.parseObject(strJson);
    }
    @ResponseBody
    @RequestMapping("/import")
    public List<Map<String,Object>> importByProvince (@RequestParam String Province){
        return dataTodayRepository.findCVImportDataTodayByProvince(Province);

    }
    @GetMapping("/getTencentWorldData/today")
    public JSONObject getTencentWorldDataToday() throws IOException {
        String strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/automation/modules/list?" +
                "modules=FAutoGlobalStatis,FAutoGlobalDailyList,FAutoCountryConfirmAdd")
                .ignoreContentType(true)
                .execute()
                .body();
        return JSONObject.parseObject(strJson).getJSONObject("data").getJSONObject("FAutoGlobalStatis");
    }
    @GetMapping("/getTencentCountryHistory")
    public JSONObject getTencentCountryHistory(@RequestParam String country) throws IOException {
        String strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/automation/foreign/daily/list?country="+country)
                .ignoreContentType(true)
                .execute()
                .body();
        return JSON.parseObject(strJson);
    }
    @GetMapping("/getWorldTotalHistory")
    public JSONObject getWorldTotalHistory() throws IOException {
        String strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/automation/modules/list?modules=FAuto" +
                "GlobalStatis,FAutoGlobalDailyList,FAutoCountryConfirmAdd")
                .ignoreContentType(true)
                .execute()
                .body();
        return JSON.parseObject(strJson);
    }
}
