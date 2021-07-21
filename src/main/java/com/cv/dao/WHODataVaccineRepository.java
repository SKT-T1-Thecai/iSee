package com.cv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cv.entity.WHODataVaccine;

import java.util.List;

public interface WHODataVaccineRepository extends JpaRepository<WHODataVaccine,Integer> {

    WHODataVaccine findWHODataVaccineByCOUNTRY(String COUNTRY);

}
