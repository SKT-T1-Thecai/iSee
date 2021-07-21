package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@DynamicUpdate
@DynamicInsert
@Entity
public class question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int qid;

    private int uid;
    private String qtitle;
    private String qcontent;
    private Date qtime;

    public int getQid() {
        return qid;
    }

    public int getUid() {
        return uid;
    }

    public String getQcontent() {
        return qcontent;
    }

    public Date getQtime() {
        return qtime;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public void setQtime(Date qtime) {
        this.qtime = qtime;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }
}
