package com.oscar.sanchez.tqs1.repository;

import com.oscar.sanchez.tqs1.classes.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {
    List<City> findByName(String name);
    List<City> findByCountry(String name);
}
