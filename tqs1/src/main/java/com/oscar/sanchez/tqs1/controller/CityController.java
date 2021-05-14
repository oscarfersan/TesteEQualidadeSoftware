package com.oscar.sanchez.tqs1.controller;

import com.oscar.sanchez.tqs1.classes.City;
import com.oscar.sanchez.tqs1.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CityController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CityRepository cityRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        try {
            LOGGER.info("Getting all city list");
            List<City> cities = new ArrayList<City>();
            cities = cityRepository.findAll();
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/city/id/{locationId}")
    public ResponseEntity<City> getCityByLocationId(@PathVariable("locationId") String locationId) {
        Optional<City> city = cityRepository.findById(locationId);
        LOGGER.info("Getting city by its locationId");
        if (city.isPresent()) {
            LOGGER.info("City found in repository");
            return new ResponseEntity<>(city.get(), HttpStatus.OK);
        } else {
            LOGGER.error("City not found in repository");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/city/name/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable("name") String name) {
        LOGGER.info("Getting city by its name");
        try {
            List<City> cities = cityRepository.findByName(name);
            if (cities.isEmpty()) {
                LOGGER.error("City " + name + " not found in repository");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOGGER.info("Returning " + name);
            return new ResponseEntity(cities, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable("country") String country) {
        LOGGER.info("Getting cities by their country");
        try {
            List<City> cities = cityRepository.findByCountry(country);
            if (cities.isEmpty()) {
                LOGGER.error("Country " + country + " not found in repository");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOGGER.info("Returning " + country + " appearances");
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/city/random")
    public ResponseEntity<City> getRandomCity() {
        LOGGER.info("Getting randmom city");
        try {
            List<City> cities = cityRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
            List<City> distinct = cities.stream().distinct().collect(Collectors.toList());
            Collections.shuffle(distinct);
            Random rand = new Random();
            City randomElement = distinct.get(rand.nextInt(distinct.size()));
            LOGGER.info("Returning " + randomElement.getName());
            return new ResponseEntity(randomElement, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/city/ranking")
    public ResponseEntity<List<City>> getCityRanking() {
        LOGGER.info("Getting ranking desc");
        try {
            List<City> cities = cityRepository.findAll(Sort.by(Sort.Direction.DESC, "value"));
            if (cities.isEmpty()) {
                LOGGER.error("There aren´t cities");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOGGER.info("Returning ranking");
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/flush")
    public ResponseEntity flushDatabase() {
        LOGGER.info("Flushing all database");
        try {
            cityRepository.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/flush/id/{locationId}")
    public ResponseEntity flushCity(@PathVariable("locationId") String locationId) {
        try {
            LOGGER.info("Flushing "+locationId);
            cityRepository.delete(cityRepository.getOne(locationId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
