package com.cv.dao;

import com.cv.entity.CVDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataHistoryRepository extends JpaRepository<CVDataHistory,Integer> {
    @Query(value = "SELECT * FROM cvdatahistory WHERE date(date)= ? and province= ?",nativeQuery = true)
    List<CVDataHistory> findCVDataHistoriesByDateAndProvince(String dateStr, String province);
    @Query(value = "SELECT * FROM cvdatahistory WHERE date(date)= ?",nativeQuery = true)
    List<CVDataHistory> findCVDataHistoriesByDateStr(String dateStr);
    @Query(value = "SELECT * FROM cvdatahistory WHERE date(date)= ? and level = ?",nativeQuery = true)
    List<CVDataHistory> findCVDataHistoriesByDateStrAndLevel(String dateStr,int level);
    List<CVDataHistory> findCVDataHistoriesByCountryAndLevel(String country, Integer level);
}
