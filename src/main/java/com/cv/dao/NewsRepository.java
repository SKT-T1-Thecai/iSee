package com.cv.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cv.entity.News;

public interface NewsRepository extends JpaRepository<News,Long>{

	ArrayList<News> findBytype(String Type);
	ArrayList<News> findByarea(String Area);
}
