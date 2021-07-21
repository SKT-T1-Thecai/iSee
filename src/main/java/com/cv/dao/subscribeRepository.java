package com.cv.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cv.entity.subscribe;

@Repository
public interface subscribeRepository extends JpaRepository<subscribe,Long>{
	
	ArrayList<subscribe> findBycity(String city);
	ArrayList<subscribe> findByuid(int uid);
}
