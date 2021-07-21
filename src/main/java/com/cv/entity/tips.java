package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@DynamicUpdate
@DynamicInsert
@Entity
public class tips {
    @Id
    private int tid;

    private String ttitle;
    private String tcontent;
    private Time ttime;

    public int getTid() {
        return tid;
    }

    public String getTtitle() {
        return ttitle;
    }

    public String getTcontent() {
        return tcontent;
    }

    public Time getTtime() {
        return ttime;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public void setTtime(Time ttime) {
        this.ttime = ttime;
    }
}
