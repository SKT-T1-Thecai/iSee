package com.cv.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cv.dao.airlineRepository;
import com.cv.entity.airline;

@RestController
public class airlineController {
	@Autowired
	airlineRepository airlinejpa;
	
	@CrossOrigin
	@RequestMapping("/airline")
	public ArrayList<airline> getairlinebtcity(String scity,String ecity){
		return airlinejpa.findByscity1Andfcity(scity, ecity);
	}
}
