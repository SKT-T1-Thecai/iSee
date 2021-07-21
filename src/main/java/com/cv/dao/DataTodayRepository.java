package com.cv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cv.entity.CVDataToday;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DataTodayRepository extends JpaRepository<CVDataToday,Integer> {
    List<CVDataToday> findByLevelAndCountry(Integer level, String country);
    List<CVDataToday> findByLevelAndProvince(Integer level, String province);
    List<CVDataToday> findByLevelAndCityAndProvince(Integer level, String city, String province);
    @Query(value = "SELECT * FROM cvdatatoday WHERE date(date)= ? and level= ?",nativeQuery = true)
    List<CVDataToday> test(String dateStr,int level);
    List<CVDataToday> findByLevelAndCity(Integer level, String city);
    @Query(value = "SELECT province,nowConfirmNum,newConfirmNum FROM cvdatatoday WHERE province = ? && city = '境外输入'",nativeQuery = true)
    List<Map<String,Object>> findCVImportDataTodayByProvince (String province);
}
