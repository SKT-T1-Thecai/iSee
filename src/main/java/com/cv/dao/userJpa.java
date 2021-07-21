package com.cv.dao;

import com.cv.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public interface userJpa extends JpaRepository<user,Integer> {
    /*判断邮箱和密码是否匹配*/
    @Query(value = "SELECT * FROM user WHERE email=? AND pwd=?",nativeQuery = true)
    Map<String,Object> loginByEmail(String email,String pwd);

    /*判断用户名和密码是否匹配*/
    @Query(value = "SELECT * FROM user WHERE uname=? AND pwd=?",nativeQuery = true)
    Map<String,Object> loginByUsername(String uname,String pwd);

    /*判断用户名和密码是否匹配*/
    @Query(value = "SELECT * FROM user WHERE email=?",nativeQuery = true)
    Map<String,Object> emailCheck(String email);

    /*展示用户信息*/
    @Query(value = "SELECT uid,uname,email,authority FROM user WHERE uid=?",nativeQuery = true)
    Map<String,Object> showInfo(int uid);

    /*修改用户信息*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET uname=?,email=? WHERE uid=?",nativeQuery = true)
    void modifyInfo(String uname,String email,int uid);

    /*机构认证权限*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET authority=1 WHERE uid=?",nativeQuery = true)
    void authorization(int uid);

    /*注册用户*/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user VALUES(default ,?,?,?,0)",nativeQuery = true)
    void register(String uname,String email,String pwd);

    /*查询用户认证等级*/
    @Query(value = "SELECT authority FROM user WHERE uid=?",nativeQuery = true)
    Map<String,Object> showAuthority(int uid);

    user findByEmailAndPwd(String email, String pwd);
}
