package com.cv.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
public class WHODataVaccine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer did;
    @JSONField(name = "country")
    private String COUNTRY;
    @JSONField(name = "iso3")
    private String ISO3;
    @JSONField(name = "who_region")
    private String WHO_REGION;
    @JSONField(name = "data_source")
    private String DATA_SOURCE;
    @JSONField(name = "date_updated")
    private Date DATE_UPDATED;
    @JSONField(name = "total_vaccinations")
    private Integer TOTAL_VACCINATIONS;
    @JSONField(name="persons_vaccinated_1plus_dose")
    private Integer PERSONS_VACCINATED_1PLUS_DOSE;
    @JSONField(name="total_vaccinations_per100")
    private Double TOTAL_VACCINATIONS_PER100;
    @JSONField(name = "persons_vaccinated_1plus_dose_per100")
    private Double PERSONS_VACCINATED_1PLUS_DOSE_PER100;
    @JSONField(name = "vaccines_used")
    private String VACCINES_USED;
    @JSONField(name = "first_vaccine_date")
    private Date FIRST_VACCINE_DATE;
    @JSONField(name = "number_vaccines_types_used")
    private Integer NUMBER_VACCINES_TYPES_USED;
    @JSONField(name = "persons_fully_vaccinated")
    private Integer PERSONS_FULLY_VACCINATED;
    @JSONField(name="persons_fully_vaccinated_per100")
    private Double PERSONS_FULLY_VACCINATED_PER100;





}
