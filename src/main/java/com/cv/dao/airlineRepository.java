package com.cv.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cv.entity.airline;

@Repository
public interface airlineRepository extends JpaRepository<airline,Long>{
	
	@Query(value="select * from airline where scity1 like %?1% and fcity like %?2%", nativeQuery = true)
	ArrayList<airline> findByscity1Andfcity(String city1,String city2);
}
