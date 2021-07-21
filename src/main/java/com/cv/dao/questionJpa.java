package com.cv.dao;

import com.cv.entity.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface questionJpa extends JpaRepository<question,Integer> {
    /*显示问题列表*/
    @Query(value = "SELECT qid, qtitle, qcontent, qtime, user.uid AS uid, uname FROM question,user WHERE user.uid=question.uid ORDER BY qtime DESC",nativeQuery = true)
    List<Map<String,Object>> questionList();

    /*显示问题详情*/
    @Query(value = "SELECT qid, qtitle, qcontent, qtime, user.uid AS uid, uname FROM question,user WHERE qid=? AND user.uid=question.uid",nativeQuery = true)
    List<Map<String,Object>> questionDetail(int qid);

    /*提问*/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO question(qid,uid,qtitle,qcontent,qtime) VALUES(DEFAULT,?,?,?,NOW())",nativeQuery = true)
    void askQuestion(int uid,String qtitle, String qcontent);

    /*删除问题*/
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM question WHERE qid=?",nativeQuery = true)
    void deleteQuestion();

    /*查询用户的问题列表*/
    @Query(value = "SELECT * FROM question WHERE uid=? ORDER BY qtime DESC",nativeQuery = true)
    List<Map<String,Object>> questionListOfUser(int uid);

    /*按主题或内容查询问题*/
    @Query(value = "SELECT * FROM question WHERE qtitle LIKE ? OR qcontent LIKE ? ORDER BY qtime DESC",nativeQuery = true)
    List<Map<String,Object>> questionSearch(String key1, String key2);
}
