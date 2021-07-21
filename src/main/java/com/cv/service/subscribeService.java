package com.cv.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.dao.subscribeRepository;
import com.cv.entity.subscribe;

@Service
public class subscribeService {
	@Autowired
	subscribeRepository subjpa;
	
	public subscribe findsublist(int uid,String city){
		ArrayList<subscribe> sub=subjpa.findBycity(city);
		for(int i=0;i<sub.size();i++) {
			if(sub.get(i).getUid()==uid) {
				return sub.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<subscribe> findsublistbyuid(int uid){
		return subjpa.findByuid(uid);
	}
}
