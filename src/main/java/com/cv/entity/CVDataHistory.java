package com.cv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class CVDataHistory  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer dhId;
    private String country;
    private String province;
    private String city;
    private Integer level;
    private Date date;

    private Integer allImportedNum;
    private Integer newImportedNum;

    private Integer nowConfirmNum;
    private Integer newConfirmNum;
    private Integer historyConfirmNum;

    private Integer allWzzNum;
    private Integer newWzzNum;

    private Integer allHealNum;
    private Integer newHealNum;
    private Double healRate;

	public Double getHealRate() {
		return healRate;
	}

	public void setHealRate(Double healRate) {
		this.healRate = healRate;
	}

	private Integer suspectNum;

    private Integer newDeadNum;
    private Integer allDeadNum;
    private double  deadRate;

    private Integer vaccineNum;

	public Integer getDhId() {
		return dhId;
	}

	public void setDhId(Integer dhId) {
		this.dhId = dhId;
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

	public Integer getNewWzzNum() {
		return newWzzNum;
	}

	public void setNewWzzNum(Integer newWzzNum) {
		this.newWzzNum = newWzzNum;
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


}
