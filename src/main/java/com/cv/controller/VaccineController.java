package com.cv.controller;

import com.alibaba.fastjson.JSONObject;
import com.cv.dao.WHODataVaccineRepository;
import com.cv.entity.WHODataVaccine;
import com.cv.entity.WorldData;
import com.cv.utils.CountryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VaccineController {
    @Autowired
    private WHODataVaccineRepository whoDataVaccineRepository;
    @GetMapping("/whodata/vaccine")
    public JSONObject getVaccineWhoData()
    {
        JSONObject res = new JSONObject();

        try {
            List<WHODataVaccine> resList = whoDataVaccineRepository.findAll();
            res.put("msg","success");
            res.put("data",resList);
        }catch (Exception e)
        {res.put("msg","failed");
        }
        return res;
    }
    @GetMapping("/whodata/vaccine/flush")
    public String flush(){
        List<WHODataVaccine> all = whoDataVaccineRepository.findAll();
        for(WHODataVaccine whoDataVaccine:all)
        {
            whoDataVaccine.setCOUNTRY(CountryUtils.getCountryCN(whoDataVaccine.getISO3()));
        }
        whoDataVaccineRepository.saveAll(all);
        return "success";
    }
    @GetMapping("/whodata/vaccine_country")
    public JSONObject getWhoVaccineDataByCountry(String country)
    {
        JSONObject res = new JSONObject();

        try {
            WHODataVaccine whoDataVaccine = whoDataVaccineRepository.
                    findWHODataVaccineByCOUNTRY(country);
            res.put("msg","success");
            res.put("data",whoDataVaccine);
        }catch (Exception e)
        {res.put("msg","failed");
        }
        return res;
    }

}
