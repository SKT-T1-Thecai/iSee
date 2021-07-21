package com.cv.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Proxy(lazy = false)
public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tid;
    @OneToOne
    private user user;
    private String tokenStr;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public com.cv.entity.user getUser() {
        return user;
    }

    public void setUser(com.cv.entity.user user) {
        this.user = user;
    }

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }
}




