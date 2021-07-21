package com.cv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cv.dao.DataTodayRepository;
import com.cv.dao.TokenRepository;
import com.cv.dao.subscribeRepository;
import com.cv.entity.CVDataToday;
import com.cv.entity.Token;
import com.cv.entity.subscribe;
import com.cv.service.subscribeService;

@RestController
public class subscribeController {
	@Autowired
	subscribeService subservice;
	
	@Autowired
	subscribeRepository subjpa;
	
	@Autowired
	TokenRepository Tokenjpa;

	@Autowired
	DataTodayRepository datajpa;
	
	@CrossOrigin
	@RequestMapping("/substatus")
	public boolean getsubstatus(String token,String city,int level){
		List<Token> toke =Tokenjpa.findTokensByTokenStr(token);
		if(toke.size()!=0) {
			int uid=toke.get(0).getUser().getUid();
			if(subservice.findsublist(uid, city)!=null) {
				return true;
			}
			return false;
		}
		return false;
	}

	
	@CrossOrigin
	@RequestMapping("/sublist")
	public ArrayList<CVDataToday> getsublist(String token){
		List<Token> toke =Tokenjpa.findTokensByTokenStr(token);
		if(toke.size()!=0) {
			ArrayList<subscribe> sublist=subservice.findsublistbyuid(toke.get(0).getUser().getUid());
			if(sublist.size()==0) {
				return null;
			}
			else {
				ArrayList<CVDataToday> dl=new ArrayList<CVDataToday>();
				for(int i=0;i<sublist.size();i++) {
					if(sublist.get(i).getLevel()==0) {
						dl.add(datajpa.findByLevelAndCountry(0, sublist.get(i).getCity()).get(0));
					}
					else if(sublist.get(i).getLevel()==1) {
						dl.add(datajpa.findByLevelAndProvince(1, sublist.get(i).getCity()).get(0));
					}
					else if(sublist.get(i).getLevel()==2) {
						dl.add(datajpa.findByLevelAndCity(2, sublist.get(i).getCity()).get(0));
					}
				}
				return dl;
			}
		}
		return null;
	}
	
	@CrossOrigin
	@RequestMapping("/subscribe")
	public String subscribe(String token,String city ,int level){
		List<Token> toke =Tokenjpa.findTokensByTokenStr(token);
		if(toke.size()!=0) {
			int uid=toke.get(0).getUser().getUid();
			if(subservice.findsublist(uid, city)!=null) {
				return "您已订阅"+city+",请勿重复订阅";
			}
			subscribe sub=new subscribe();
			sub.setUid(uid);
			sub.setCity(city);
			sub.setLevel(level);
			subjpa.saveAndFlush(sub);
			return "订阅成功";
		}
		return "未登录";
	}
	
	@CrossOrigin
	@RequestMapping("/subscribe/cancel")
	public String subscribecancel(String token,String city){
		List<Token> toke =Tokenjpa.findTokensByTokenStr(token);
		if(toke.size()!=0) {
			int uid=toke.get(0).getUser().getUid();
			subscribe sub=subservice.findsublist(uid, city);
			if(sub==null) {
				return "您还未订阅"+city;
			}
			subjpa.delete(sub);
			return "取消订阅成功";
		}
		return "未登录";
	
	}
}
