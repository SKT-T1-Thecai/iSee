package com.cv.dao;

import com.cv.entity.answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface answerJpa extends JpaRepository<answer,Integer> {
    /*显示一个问题下的所有回答*/
    @Query(value = "SELECT aid, qid, acontent, atime, priority, user.uid AS uid, uname FROM answer,user WHERE answer.qid=? AND answer.uid=user.uid ORDER BY atime DESC",nativeQuery = true)
    List<Map<String,Object>> answerList(int qid);

    /*回答问题*/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO answer(aid,qid,acontent,uid,atime,priority) VALUES(DEFAULT,?,?,?,NOW(),?)",nativeQuery = true)
    void addAnswer(int qid, String acontent,int uid,int priority);

    /*删除回答*/
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM answer WHERE aid=?",nativeQuery = true)
    void deleteAnswer();
}
