package com.cv.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cv.entity.News;
import com.cv.service.NewsService;

@RestController
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
	@CrossOrigin
	@RequestMapping("/newT")
	public ArrayList<News> getnewsbytype(@RequestParam String Type){
		return newsService.getNewsBytype(Type);
	}
	@CrossOrigin
	@RequestMapping("/newA")
	public ArrayList<News> getnewsbyarea(@RequestParam String Area){
		return newsService.getNewsByarea(Area);
	}
	@CrossOrigin
	@RequestMapping("/newST")
	public ArrayList<News> getsortedtype(String type){
		return newsService.sortbytime(newsService.getNewsBytype(type));
	}
	@CrossOrigin
	@RequestMapping("/newSA")
	public ArrayList<News> getsortedarea(String area){
		return newsService.sortbytime(newsService.getNewsByarea(area));
	}
}