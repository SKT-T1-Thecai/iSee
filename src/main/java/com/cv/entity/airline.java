package com.cv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class airline {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String airid1;
	private String aircode1;
	private String sportname1;
	private String eportname1;
	private String companycode1;
	private String stime1;
	private String etime1;
	private String jixing1;
	private String scountry1;
	private String scity1;
	private String ecountry1;
	private String ecity1;
	private String airid2;
	private String aircode2;
	private String sportname2;
	private String eportname2;
	private String companycode2;
	private String stime2;
	private String etime2;
	private String jixing2;
	private String scountry2;
	private String scity2;
	private String ecountry2;
	private String ecity2;
	private String airid3;
	private String aircode3;
	private String sportname3;
	private String eportname3;
	private String companycode3;
	private String stime3;
	private String etime3;
	private String jixing3;
	private String scountry3;
	private String scity3;
	private String ecountry3;
	private String ecity3;
	private String airid4;
	private String aircode4;
	private String sportname4;
	private String eportname4;
	private String companycode4;
	private String stime4;
	private String etime4;
	private String jixing4;
	private String scountry4;
	private String scity4;
	private String ecountry4;
	private String ecity4;
	private int len;
	private String fcity;
	public String getFcity() {
		return fcity;
	}
	public void setFcity(String fcity) {
		this.fcity = fcity;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAirid1() {
		return airid1;
	}
	public void setAirid1(String airid1) {
		this.airid1 = airid1;
	}
	public String getAircode1() {
		return aircode1;
	}
	public void setAircode1(String aircode1) {
		this.aircode1 = aircode1;
	}
	public String getSportname1() {
		return sportname1;
	}
	public void setSportname1(String sportname1) {
		this.sportname1 = sportname1;
	}
	public String getEportname1() {
		return eportname1;
	}
	public void setEportname1(String eportname1) {
		this.eportname1 = eportname1;
	}
	public String getCompanycode1() {
		return companycode1;
	}
	public void setCompanycode1(String companycode1) {
		this.companycode1 = companycode1;
	}
	public String getStime1() {
		return stime1;
	}
	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}
	public String getEtime1() {
		return etime1;
	}
	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}
	public String getJixing1() {
		return jixing1;
	}
	public void setJixing1(String jixing1) {
		this.jixing1 = jixing1;
	}
	public String getScountry1() {
		return scountry1;
	}
	public void setScountry1(String scountry1) {
		this.scountry1 = scountry1;
	}
	public String getScity1() {
		return scity1;
	}
	public void setScity1(String scity1) {
		this.scity1 = scity1;
	}
	public String getEcountry1() {
		return ecountry1;
	}
	public void setEcountry1(String ecountry1) {
		this.ecountry1 = ecountry1;
	}
	public String getEcity1() {
		return ecity1;
	}
	public void setEcity1(String ecity1) {
		this.ecity1 = ecity1;
	}
	public String getAirid2() {
		return airid2;
	}
	public void setAirid2(String airid2) {
		this.airid2 = airid2;
	}
	public String getAircode2() {
		return aircode2;
	}
	public void setAircode2(String aircode2) {
		this.aircode2 = aircode2;
	}
	public String getSportname2() {
		return sportname2;
	}
	public void setSportname2(String sportname2) {
		this.sportname2 = sportname2;
	}
	public String getEportname2() {
		return eportname2;
	}
	public void setEportname2(String eportname2) {
		this.eportname2 = eportname2;
	}
	public String getCompanycode2() {
		return companycode2;
	}
	public void setCompanycode2(String companycode2) {
		this.companycode2 = companycode2;
	}
	public String getStime2() {
		return stime2;
	}
	public void setStime2(String stime2) {
		this.stime2 = stime2;
	}
	public String getEtime2() {
		return etime2;
	}
	public void setEtime2(String etime2) {
		this.etime2 = etime2;
	}
	public String getJixing2() {
		return jixing2;
	}
	public void setJixing2(String jixing2) {
		this.jixing2 = jixing2;
	}
	public String getScountry2() {
		return scountry2;
	}
	public void setScountry2(String scountry2) {
		this.scountry2 = scountry2;
	}
	public String getScity2() {
		return scity2;
	}
	public void setScity2(String scity2) {
		this.scity2 = scity2;
	}
	public String getEcountry2() {
		return ecountry2;
	}
	public void setEcountry2(String ecountry2) {
		this.ecountry2 = ecountry2;
	}
	public String getEcity2() {
		return ecity2;
	}
	public void setEcity2(String ecity2) {
		this.ecity2 = ecity2;
	}
	public String getAirid3() {
		return airid3;
	}
	public void setAirid3(String airid3) {
		this.airid3 = airid3;
	}
	public String getAircode3() {
		return aircode3;
	}
	public void setAircode3(String aircode3) {
		this.aircode3 = aircode3;
	}
	public String getSportname3() {
		return sportname3;
	}
	public void setSportname3(String sportname3) {
		this.sportname3 = sportname3;
	}
	public String getEportname3() {
		return eportname3;
	}
	public void setEportname3(String eportname3) {
		this.eportname3 = eportname3;
	}
	public String getCompanycode3() {
		return companycode3;
	}
	public void setCompanycode3(String companycode3) {
		this.companycode3 = companycode3;
	}
	public String getStime3() {
		return stime3;
	}
	public void setStime3(String stime3) {
		this.stime3 = stime3;
	}
	public String getEtime3() {
		return etime3;
	}
	public void setEtime3(String etime3) {
		this.etime3 = etime3;
	}
	public String getJixing3() {
		return jixing3;
	}
	public void setJixing3(String jixing3) {
		this.jixing3 = jixing3;
	}
	public String getScountry3() {
		return scountry3;
	}
	public void setScountry3(String scountry3) {
		this.scountry3 = scountry3;
	}
	public String getScity3() {
		return scity3;
	}
	public void setScity3(String scity3) {
		this.scity3 = scity3;
	}
	public String getEcountry3() {
		return ecountry3;
	}
	public void setEcountry3(String ecountry3) {
		this.ecountry3 = ecountry3;
	}
	public String getEcity3() {
		return ecity3;
	}
	public void setEcity3(String ecity3) {
		this.ecity3 = ecity3;
	}
	public String getAirid4() {
		return airid4;
	}
	public void setAirid4(String airid4) {
		this.airid4 = airid4;
	}
	public String getAircode4() {
		return aircode4;
	}
	public void setAircode4(String aircode4) {
		this.aircode4 = aircode4;
	}
	public String getSportname4() {
		return sportname4;
	}
	public void setSportname4(String sportname4) {
		this.sportname4 = sportname4;
	}
	public String getEportname4() {
		return eportname4;
	}
	public void setEportname4(String eportname4) {
		this.eportname4 = eportname4;
	}
	public String getCompanycode4() {
		return companycode4;
	}
	public void setCompanycode4(String companycode4) {
		this.companycode4 = companycode4;
	}
	public String getStime4() {
		return stime4;
	}
	public void setStime4(String stime4) {
		this.stime4 = stime4;
	}
	public String getEtime4() {
		return etime4;
	}
	public void setEtime4(String etime4) {
		this.etime4 = etime4;
	}
	public String getJixing4() {
		return jixing4;
	}
	public void setJixing4(String jixing4) {
		this.jixing4 = jixing4;
	}
	public String getScountry4() {
		return scountry4;
	}
	public void setScountry4(String scountry4) {
		this.scountry4 = scountry4;
	}
	public String getScity4() {
		return scity4;
	}
	public void setScity4(String scity4) {
		this.scity4 = scity4;
	}
	public String getEcountry4() {
		return ecountry4;
	}
	public void setEcountry4(String ecountry4) {
		this.ecountry4 = ecountry4;
	}
	public String getEcity4() {
		return ecity4;
	}
	public void setEcity4(String ecity4) {
		this.ecity4 = ecity4;
	}
	
}
