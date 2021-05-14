package com.oscar.sanchez.tqs1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.sanchez.tqs1.cache.GenericCache;
import com.oscar.sanchez.tqs1.classes.City;
import com.oscar.sanchez.tqs1.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AirController {

    private static Logger LOGGER = LoggerFactory.getLogger(AirController.class);
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CityRepository cityRepository;

    private final GenericCache<String, City> cache = new GenericCache<String, City>();

    @GetMapping("/")
    public String home() {
        LOGGER.info("home Page");
        return "index";
    }

    @RequestMapping("/form")
    public String showForm() {
        LOGGER.info("form Page");
        return "weatherForm";
    }

    @RequestMapping("/showResults")
    public String showResults(HttpServletRequest request, Model model) {
        LOGGER.info("Getting Results");
        //https://docs.openaq.org/#/
        String city = request.getParameter("city");
        City cityParam = new City();
        if (this.cache.get(city).isPresent() && !this.cache.get(city).isEmpty() ) {
            LOGGER.info(city+" is cached");
            City aux = this.cache.get(city).get();
            cityParam = aux;
        } else {
            //api fetch
            LOGGER.info("Fetching from external api");
            RestTemplate restTemplate = new RestTemplate();
            String uri = "https://u50g7n0cbj.execute-api.us-east-1.amazonaws.com/v2/measurements?limit=1&page=1&offset=0&sort=desc&radius=1000&city=" + city + "&order_by=datetime";
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            //Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            try {
                if(result.getStatusCodeValue()==500){
                    LOGGER.error("Value not found");
                    return "error";
                }
                root = mapper.readTree(result.getBody());
                LOGGER.info(root.path("results").textValue());
                //Create the city
                String locationId = root.path("results").get(0).path("locationId").asText();
                String location = root.path("results").get(0).path("location").asText();
                String country = root.path("results").get(0).path("country").asText();
                String name = root.path("results").get(0).path("city").asText();
                String date = root.path("results").get(0).path("date").path("utc").asText();
                float latitude = Float.parseFloat(root.path("results").get(0).path("coordinates").path("latitude").asText());
                float longitude = Float.parseFloat(root.path("results").get(0).path("coordinates").path("longitude").asText());
                float value = Float.parseFloat(root.path("results").get(0).path("value").asText());
                City aux = new City(locationId, name, country, latitude, longitude, value, location,date);
                LOGGER.info(aux.toString());
                this.cache.put(name,aux);
                cityRepository.save(aux);
                LOGGER.info(aux.getName()+" saved in repository");
                cityParam = aux;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //
        }
        model.addAttribute("city", cityParam);
        LOGGER.info("Going to results page");
        return "results";
    }

}
