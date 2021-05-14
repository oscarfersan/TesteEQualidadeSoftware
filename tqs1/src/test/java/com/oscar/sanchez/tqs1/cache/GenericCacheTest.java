package com.oscar.sanchez.tqs1.cache;

import com.oscar.sanchez.tqs1.classes.City;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenericCacheTest {
    GenericCache<String,City> cache = new GenericCache<String,City>();
    @Test
    @Order(1)
    public void testCachedValue(){ //test if the value is cached succesfully
        System.out.println("TestCachedValue");
        City city = new City("EXAMPLE","Coslada","España",0.0f,0.0f,1.0f,"ES001","Today");
        cache.put(city.getName(),city);
        assertEquals(1,cache.size());
    }
    @Test
    @Order(2)
    public void testGetCachedValue(){
        System.out.println("TestGetValueCached");
        City city = new City("EXAMPLE","San Fernando","España",0.0f,0.0f,1.0f,"ES001","Today");
        cache.put(city.getName(),city);

        City aux = cache.get(city.getName()).get();
        aux.toString();
        assertEquals(aux,city);
    }
    @Test
    @Order(3)
    public void testCleanCache(){
        System.out.println("TestCleaningCache");
        cache.clear();
        assertEquals(0,cache.size());
    }

}