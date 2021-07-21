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
public class HopkinsDataToday {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer did;
    private String Province_State;
    private String Country_Region;
    private Date Last_Update;
    private Double Lat;
    private Double Long_;
    private Integer Confirmed;
    private Integer Deaths;
    private Integer Recovered;
    private Integer Active;
    private String Combined_Key;
    private Double Incident_Rate;
    private Double Case_Fatality_Ratio;


}
