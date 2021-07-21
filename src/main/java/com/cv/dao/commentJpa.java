package com.cv.dao;

import com.cv.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface commentJpa extends JpaRepository<comment,Integer> {
    /*显示一个回答下的所有评论*/
    @Query(value = "SELECT cid, aid, ccontent, ctime, user.uid AS uid,uname FROM comment,user WHERE aid=? AND user.uid=comment.uid ORDER BY ctime DESC",nativeQuery = true)
    List<Map<String,Object>> commentOfAnswer(int aid);

    /*评论*/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment VALUES(DEFAULT,?,?,?,NOW())",nativeQuery = true)
    void comment(int aid, String ccontent, int uid);

    /*删除评论*/
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM comment WHERE cid=?",nativeQuery = true)
    void deleteComment(int cid);
}
