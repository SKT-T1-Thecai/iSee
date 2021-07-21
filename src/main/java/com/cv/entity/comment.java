package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@DynamicUpdate
@DynamicInsert
@Entity
public class comment {
    @Id
    private int cid;

    private int aid;
    private String ccontent;
    private int uid;
    private Time ctime;

    public int getCid() {
        return cid;
    }

    public int getAid() {
        return aid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public int getUid() {
        return uid;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }
}
