package com.cv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class WHODataCase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer did;
    private Date Date_reported;
    private String Country_code;
    private String Country;
    private String WHO_region;
    private Integer New_cases;
    private Integer Cumulative_cases;
    private Integer New_deaths;
    private Integer Cumulative_deaths;


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Date getDate_reported() {
        return Date_reported;
    }

    public void setDate_reported(Date date_reported) {
        Date_reported = date_reported;
    }

    public String getCountry_code() {
        return Country_code;
    }

    public void setCountry_code(String country_code) {
        Country_code = country_code;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getWHO_region() {
        return WHO_region;
    }

    public void setWHO_region(String WHO_region) {
        this.WHO_region = WHO_region;
    }

    public Integer getNew_cases() {
        return New_cases;
    }

    public void setNew_cases(Integer new_cases) {
        New_cases = new_cases;
    }

    public Integer getCumulative_cases() {
        return Cumulative_cases;
    }

    public void setCumulative_cases(Integer cumulative_cases) {
        Cumulative_cases = cumulative_cases;
    }

    public Integer getNew_deaths() {
        return New_deaths;
    }

    public void setNew_deaths(Integer new_deaths) {
        New_deaths = new_deaths;
    }

    public Integer getCumulative_deaths() {
        return Cumulative_deaths;
    }

    public void setCumulative_deaths(Integer cumulative_deaths) {
        Cumulative_deaths = cumulative_deaths;
    }
}
