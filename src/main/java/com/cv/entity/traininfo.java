package com.cv.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@DynamicUpdate
@DynamicInsert
@Entity
public class traininfo {
    @Id
    private int f1;

    private String trainnumber;
    private String station;
    private int sorder;
    private String city;
    private String location;

    public int getF1() {
        return f1;
    }

    public String getTrainnumber() {
        return trainnumber;
    }

    public String getStation() {
        return station;
    }

    public int getSorder() {
        return sorder;
    }

    public String getCity() {
        return city;
    }

    public void setTrainnumber(String trainnumber) {
        this.trainnumber = trainnumber;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setSorder(int sorder) {
        this.sorder = sorder;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
