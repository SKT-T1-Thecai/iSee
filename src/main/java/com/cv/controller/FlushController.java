package com.cv.controller;

import com.cv.entity.WHODataVaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class FlushController {

    @Autowired
    DataTodayImportController dataTodayImportController;
    @Autowired
    DataHistoryImportController dataHistoryImportController;
    @Autowired
    VaccineController vaccineController;
    @Autowired
    WorldOwidController worldOwidController;
    @GetMapping("/flushAll")
    public void flushAll() throws ParseException {
        dataHistoryImportController.importHistory();
        dataTodayImportController.importToday();
        dataHistoryImportController.generateChinaHistory();
        vaccineController.flush();
        worldOwidController.flush();
        for(int i=0;i<20;i++)
            System.out.println("success");
    }
}
