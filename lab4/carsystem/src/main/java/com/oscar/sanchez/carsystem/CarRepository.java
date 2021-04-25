package com.oscar.sanchez.carsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
