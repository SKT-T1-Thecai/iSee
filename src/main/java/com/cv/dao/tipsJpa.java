package com.cv.dao;

import com.cv.entity.tips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface tipsJpa extends JpaRepository<tips,Integer> {
    /*发布小知识*/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tips VALUES(DEFAULT,?,?,NOW())",nativeQuery = true)
    void addTips(String title,String tcontent);

    /*显示小知识列表*/
    @Query(value = "SELECT * FROM tips ORDER BY ttime DESC",nativeQuery = true)
    List<Map<String,Object>> showTips();

    /*显示小知识内容*/
    @Query(value = "SELECT * FROM tips WHERE tid=?",nativeQuery = true)
    List<Map<String,Object>> tipsDetail(int tid);

    /*删除小知识*/
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tips WHERE tid=?",nativeQuery = true)
    void deleteTips(int tid);
}
