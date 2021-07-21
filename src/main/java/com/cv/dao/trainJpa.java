package com.cv.dao;

import com.cv.entity.traininfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface trainJpa extends JpaRepository<traininfo,Integer> {
    /*查询某城市的所有站点*/
    @Query(value = "SELECT station FROM stationcity WHERE city LIKE ?",nativeQuery = true)
    List<Map<String,Object>> showStationsByCity(String city);

    /*查询经过某站点的所有车次*/
    @Query(value = "SELECT trainnumber FROM traininfo WHERE station LIKE ?",nativeQuery = true)
    List<Map<String,Object>> showTrainnumberByStation(String train);

    /*查询经过某城市的所有车次*/
    @Query(value = "SELECT trainnumber,city FROM traininfo WHERE city LIKE ? ORDER BY trainnumber",nativeQuery = true)
    List<Map<String,Object>> showTrainnumberByCity(String city);

    /*查询某车次的所有站点及其所在城市*/
    @Query(value = "SELECT station,city,sorder,location FROM traininfo WHERE trainnumber=? ORDER BY sorder",nativeQuery = true)
    List<Map<String,Object>> showRouteByTrainnumber(String trainnumber);

    /*查询某站点所在城市*/
    @Query(value = "SELECT city FROM traininfo WHERE trainnumber=?",nativeQuery = true)
    List<Map<String,Object>> showCityByStation(String station);

    /*查询某车次某站次后的所有站次及城市*/
    @Query(value = "SELECT station,city,sorder FROM traininfo WHERE trainnumber=? AND sorder>=? ORDER BY sorder",nativeQuery = true)
    List<Map<String,Object>> showAfter(String trainnumber,int sorder);

    /*查询某城市在某车次的站次*/
    @Query(value = "SELECT station,sorder FROM traininfo WHERE city LIKE ? AND trainnumber=?",nativeQuery = true)
    List<Map<String,Object>> showCityOrder(String city,String trainnumber);

}
