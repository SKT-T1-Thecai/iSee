package com.cv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Proxy(lazy = false)
public class WorldData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int did;
    private String iso_code;
    private String continent;
    private String location;
    private Date date;
    private Integer total_cases;
    private Integer new_cases;
    private double new_cases_smoothed;
    private Integer total_deaths;
    private Integer new_deaths;
    private double new_deaths_smoothed;
    private double total_cases_per_million;
    private double new_cases_per_million;
    private double new_cases_smoothed_per_million;
    private double total_deaths_per_million;
    private double new_deaths_per_million;
    private double new_deaths_smoothed_per_million;
    private double reproduction_rate;
    private Integer icu_patients;
    private double icu_patients_per_million;
    private Integer hosp_patients;
    private double hosp_patients_per_million;
    private Integer weekly_icu_admissions;
    private double weekly_icu_admissions_per_million;
    private Integer weekly_hosp_admissions;
    private Integer weekly_hosp_admissions_per_million;
    private Integer new_tests;
    private Integer total_tests;
    private double total_tests_per_thousand;
    private double new_tests_per_thousand;
    private Integer new_tests_smoothed;
    private double new_tests_smoothed_per_thousand;
    private double positive_rate;
    private double tests_per_case;
    private String tests_units;
    private long total_vaccinations;
    private Integer people_vaccinated;
    private Integer people_fully_vaccinated;
    private Integer new_vaccinations;
    private double new_vaccinations_smoothed;
    private double total_vaccinations_per_hundred;
    private double people_vaccinated_per_hundred;
    private double people_fully_vaccinated_per_hundred;
    private double new_vaccinations_smoothed_per_million;
    private double stringency_index;
    private long population;
    private double population_density;
    private double median_age;
    private double aged_65_older;
    private double aged_70_older;
    private double gdp_per_capita;
    private double extreme_poverty;
    private double cardiovasc_death_rate;
    private double diabetes_prevalence;
    private double female_smokers;
    private double male_smokers;
    private double handwashing_facilities;
    private double hospital_beds_per_thousand;
    private double life_expectancy;
    private double human_development_index;
    private double excess_mortality;

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
