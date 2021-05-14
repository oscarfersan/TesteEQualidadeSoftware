package com.oscar.sanchez.tqs1.repository;

import com.oscar.sanchez.tqs1.classes.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CityRepositoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CityRepository cityRepository;
    @Test
    public void addCityandGetIt() throws Exception{
        City aux = new City("AUX001","Coslada","ES",0.0f,0.0f,4.9f,"ES0001","Today");
        cityRepository.save(aux);
        mockMvc.perform(get("http://localhost:8080/api/cities").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].name",is("Coslada")));
        cityRepository.delete(aux);
    }
}