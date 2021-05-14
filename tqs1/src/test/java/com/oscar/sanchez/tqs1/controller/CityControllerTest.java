package com.oscar.sanchez.tqs1.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {
    String uri = "http://localhost:8080";
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testStatusConn() throws Exception{
        mockMvc.perform(get(uri+"/")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testListCities() throws Exception{
        //Check that the city list is not empty
        mockMvc.perform(get(uri+"/api/cities").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].locationId").isNotEmpty());
    }
    @Test
    public void testGetCityByLocationId() throws Exception{
        String locationId = "4306";
        mockMvc.perform(get(uri+"/api/city/id/"+locationId).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationId",is(locationId)));
    }
    @Test
    public void testListCitiesByCountry() throws Exception{
        //Check if cities can be searched by country prefix
        String prefix = "ES";
        mockMvc.perform(get(uri+"/api/country/"+prefix).accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].locationId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].country",hasItem(prefix)));
    }
    @Test
    public void testGetRandomCity() throws Exception{
        mockMvc.perform(get(uri+"/city/random").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
    }
}