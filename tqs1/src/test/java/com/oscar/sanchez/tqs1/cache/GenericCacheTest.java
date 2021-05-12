package com.oscar.sanchez.tqs1.cache;

import com.oscar.sanchez.tqs1.classes.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericCacheTest {
    GenericCache<String,City> cache = new GenericCache<String,City>();
    @Test
    @Order(1)
    public void testCachedValue(){ //test if the value is cached succesfully
        City city = new City("EXAMPLE","Coslada","España",0.0f,0.0f,1.0f,"ES001");
        cache.put(city.getName(),city);
        assertEquals(1,cache.size());
    }
    @Test
    @Order(2)
    public void testGetCachedValue(){
        City city = new City("EXAMPLE","San Fernando","España",0.0f,0.0f,1.0f,"ES001");
        cache.put(city.getName(),city);

        City aux = cache.get(city.getName()).get();
        aux.toString();
        assertEquals(aux,city);
    }
    @Test
    @Order(3)
    public void testCleanCache(){
        cache.clear();
        assertEquals(0,cache.size());
    }
    @AfterEach
    public void afterEach(){
        cache.clear();
    }
}