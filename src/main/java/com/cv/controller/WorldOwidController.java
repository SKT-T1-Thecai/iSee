package com.cv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cv.dao.WHODataVaccineRepository;
import com.cv.dao.WorldDataRepository;
import com.cv.entity.WHODataVaccine;
import com.cv.entity.WorldData;
import com.cv.utils.CountryUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorldOwidController {
    @Autowired
    private WorldDataRepository worldDataRepository;
    @Autowired
    private WHODataVaccineRepository whoDataVaccineRepository;
    @GetMapping("/worlddata/owid/newest")
    public JSONObject getNewest(){
        JSONObject res = new JSONObject();
        res.put("msg","success");
        List<JSONObject> res_list = worldDataRepository.getNewest();
        res.put("data",res_list);
        return res;
    }
    @GetMapping("/worlddata/owid/history")
    public JSONObject getHistory(String location){
        JSONObject res = new JSONObject();
        res.put("msg","success");
        List<WorldData> res_list = worldDataRepository.findByLocation(location);
        res.put("data",res_list);
        return res;
    }
    @GetMapping("/worlddata/owid/flush")
    public String flush(){
        List<WorldData> all = worldDataRepository.findAll();
        for(WorldData worldData:all)
        {
            worldData.setLocation(CountryUtils.getCountryCN(worldData.getIso_code()));
        }
        worldDataRepository.saveAll(all);
        return "success";
    }
    @GetMapping("/worlddata/owid/today/country")
    public JSONObject getWorldDataTodayByCountry(String country){
        return worldDataRepository.getTodayDataByCountry(country);
    }
    @GetMapping("/worlddata/hybrid/today/country")
    public JSONObject getHybridData(String country) throws IOException {
        String strJson = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/automation/foreign/daily/list?country="
                +country)
                .ignoreContentType(true)
                .execute()
                .body();
        System.out.println(strJson);
        JSONObject jsonValue = JSONObject.parseObject(strJson);
        List<JSONObject> countries = JSON.parseArray(jsonValue.getJSONArray("data").toString(),JSONObject.class);
        JSONObject res = countries.get(countries.size()-1);
        WHODataVaccine whoDataVaccine = whoDataVaccineRepository.findWHODataVaccineByCOUNTRY(country);
        res.put("allVaccine",whoDataVaccine.getTOTAL_VACCINATIONS());
        res.put("vaccineTypes",whoDataVaccine.getVACCINES_USED());
        res.put("total_vaccines_per_100",whoDataVaccine.getTOTAL_VACCINATIONS_PER100());
        res.put("population",worldDataRepository.getTodayDataByCountry(country).get("population"));
        res.put("nowConfirm",res.getInteger("confirm")-res.getInteger("heal")-res.getInteger("dead"));
        return res;
    }

}
