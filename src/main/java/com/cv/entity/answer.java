package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Time;

@DynamicUpdate
@DynamicInsert
@Entity
public class answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;

    private int qid;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String acontent;
    private int uid;
    private Time atime;
    private int priority;

    public int getPriority() {
        return priority;
    }

    public int getAid() {
        return aid;
    }

    public int getQid() {
        return qid;
    }

    public String getAcontent() {
        return acontent;
    }

    public int getUid() {
        return uid;
    }

    public Time getAtime() {
        return atime;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAtime(Time atime) {
        this.atime = atime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
