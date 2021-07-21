package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@DynamicUpdate
@DynamicInsert
@Entity
public class user {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int uid;

    private String uname;
    private String email;
    private String pwd;
    private int authority;

    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public int getAuthority() {
        return authority;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
