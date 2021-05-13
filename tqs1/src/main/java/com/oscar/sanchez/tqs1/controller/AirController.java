package com.oscar.sanchez.tqs1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.sanchez.tqs1.cache.GenericCache;
import com.oscar.sanchez.tqs1.classes.City;
import com.oscar.sanchez.tqs1.repository.CityRepository;
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CityRepository cityRepository;

    private final GenericCache<String, City> cache = new GenericCache<String, City>();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/form")
    public String showForm() {
        return "weatherForm";
    }

    @RequestMapping("/showResults")
    public String showResults(HttpServletRequest request, Model model) {
        //https://docs.openaq.org/#/
        String city = request.getParameter("city");
        City cityParam = new City();
        if (this.cache.get(city).isPresent() && !this.cache.get(city).isEmpty() ) {
            City aux = this.cache.get(city).get();
            cityParam = aux;
        } else {
            //api fetch
            RestTemplate restTemplate = new RestTemplate();
            String uri = "https://u50g7n0cbj.execute-api.us-east-1.amazonaws.com/v2/measurements?limit=1&page=1&offset=0&sort=desc&radius=1000&city=" + city + "&order_by=datetime";
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            //Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            try {
                if(result.getStatusCodeValue()==500){
                    return "eror";
                }
                root = mapper.readTree(result.getBody());
                System.out.println(root.path("results"));
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
                System.out.println(aux);
                this.cache.put(name,aux);
                cityRepository.save(aux);
                cityParam = aux;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //
        }
        model.addAttribute("city", cityParam);
        return "results";
    }

}
