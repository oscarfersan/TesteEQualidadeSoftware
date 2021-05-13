package com.oscar.sanchez.tqs1.controller;

import com.oscar.sanchez.tqs1.classes.City;
import com.oscar.sanchez.tqs1.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CityController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CityRepository cityRepository;


    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities(){
        try{
            List<City> cities = new ArrayList<City>();
            cities = cityRepository.findAll();
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/city/id/{locationId}")
    public ResponseEntity<City> getCityByLocationId(@PathVariable("locationId") String locationId){
        Optional<City> city = cityRepository.findById(locationId);
        if (city.isPresent()) {
            return new ResponseEntity<>(city.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/city/name/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable("name") String name){
        try{
            List<City> cities = cityRepository.findByName(name);
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(cities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/country/{country}")
    public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable("country") String country){
        try{
            List<City> cities = cityRepository.findByCountry(country);
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/city/random")
    public ResponseEntity<City> getRandomCity(){
        try{
            List<City> cities = cityRepository.findAll(Sort.by(Sort.Direction.DESC,"date"));
            List<City> distinct = cities.stream().distinct().collect(Collectors.toList());
            Collections.shuffle(distinct);
            Random rand = new Random();
            City randomElement = distinct.get(rand.nextInt(distinct.size()));
            return new ResponseEntity(randomElement, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/city/ranking")
    public ResponseEntity<List<City>> getCityRanking(){
        try{
            List<City> cities = cityRepository.findAll(Sort.by(Sort.Direction.DESC,"value"));
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/flush")
    public ResponseEntity flushDatabase(){
        try{
            cityRepository.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
