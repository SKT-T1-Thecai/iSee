package com.cv.dao;

import com.cv.entity.city;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface cityJpa extends JpaRepository<city,Integer> {
    /*查询某城市所属省份*/
    @Query(value = "SELECT * FROM city WHERE city.city LIKE ?",nativeQuery = true)
    List<Map<String,Object>> cityLocate(String city);

    /*查询某省份所有城市*/
    @Query(value = "SELECT * FROM city WHERE province LIKE ?",nativeQuery = true)
    List<Map<String,Object>> cityOfProvince(String city);
}
