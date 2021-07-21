package com.cv.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class CVDataToday {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer dtId;
    private String country;
    private String province;
    private String city;
    private Integer level;
    @LastModifiedDate
    @Column(nullable = false)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date date;

    private Integer newSevereNum;
    private Integer allSevereNum;

    private Integer allImportedNum;
    private Integer newImportedNum;

    private Integer nowConfirmNum;
    private Integer newConfirmNum;
    private Integer historyConfirmNum;

    private Integer allWzzNum;
    private Integer addWzzNum;

    private Integer allHealNum;
    private Integer newHealNum;
    private double healRate;

    private Integer suspectNum;
    private Integer newSuspectNum;

    private Integer newDeadNum;
    private Integer allDeadNum;
    private double  deadRate;

    private Integer vaccineNum;

	public Integer getDtId() {
		return dtId;
	}

	public void setDtId(Integer dtId) {
		this.dtId = dtId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNewSevereNum() {
		return newSevereNum;
	}

	public void setNewSevereNum(Integer newSevereNum) {
		this.newSevereNum = newSevereNum;
	}

	public Integer getAllSevereNum() {
		return allSevereNum;
	}

	public void setAllSevereNum(Integer allSevereNum) {
		this.allSevereNum = allSevereNum;
	}

	public Integer getAllImportedNum() {
		return allImportedNum;
	}

	public void setAllImportedNum(Integer allImportedNum) {
		this.allImportedNum = allImportedNum;
	}

	public Integer getNewImportedNum() {
		return newImportedNum;
	}

	public void setNewImportedNum(Integer newImportedNum) {
		this.newImportedNum = newImportedNum;
	}

	public Integer getNowConfirmNum() {
		return nowConfirmNum;
	}

	public void setNowConfirmNum(Integer nowConfirmNum) {
		this.nowConfirmNum = nowConfirmNum;
	}

	public Integer getNewConfirmNum() {
		return newConfirmNum;
	}

	public void setNewConfirmNum(Integer newConfirmNum) {
		this.newConfirmNum = newConfirmNum;
	}

	public Integer getHistoryConfirmNum() {
		return historyConfirmNum;
	}

	public void setHistoryConfirmNum(Integer historyConfirmNum) {
		this.historyConfirmNum = historyConfirmNum;
	}

	public Integer getAllWzzNum() {
		return allWzzNum;
	}

	public void setAllWzzNum(Integer allWzzNum) {
		this.allWzzNum = allWzzNum;
	}

	public Integer getAddWzzNum() {
		return addWzzNum;
	}

	public void setAddWzzNum(Integer addWzzNum) {
		this.addWzzNum = addWzzNum;
	}

	public Integer getAllHealNum() {
		return allHealNum;
	}

	public void setAllHealNum(Integer allHealNum) {
		this.allHealNum = allHealNum;
	}

	public Integer getNewHealNum() {
		return newHealNum;
	}

	public void setNewHealNum(Integer newHealNum) {
		this.newHealNum = newHealNum;
	}

	public Integer getSuspectNum() {
		return suspectNum;
	}

	public void setSuspectNum(Integer suspectNum) {
		this.suspectNum = suspectNum;
	}

	public Integer getNewSuspectNum() {
		return newSuspectNum;
	}

	public void setNewSuspectNum(Integer newSuspectNum) {
		this.newSuspectNum = newSuspectNum;
	}

	public Integer getNewDeadNum() {
		return newDeadNum;
	}

	public void setNewDeadNum(Integer newDeadNum) {
		this.newDeadNum = newDeadNum;
	}

	public Integer getAllDeadNum() {
		return allDeadNum;
	}

	public void setAllDeadNum(Integer allDeadNum) {
		this.allDeadNum = allDeadNum;
	}

	public double getDeadRate() {
		return deadRate;
	}

	public void setDeadRate(double deadRate) {
		this.deadRate = deadRate;
	}

	public Integer getVaccineNum() {
		return vaccineNum;
	}

	public void setVaccineNum(Integer vaccineNum) {
		this.vaccineNum = vaccineNum;
	}

	public double getHealRate() {
		return healRate;
	}

	public void setHealRate(double healRate) {
		this.healRate = healRate;
	}

}
