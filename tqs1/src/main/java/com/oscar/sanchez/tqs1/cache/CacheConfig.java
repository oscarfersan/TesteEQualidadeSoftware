package com.oscar.sanchez.tqs1.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public <K, V> IGenericCache<K, V> getCache(@Value("${app.cache-timeout}") Long cacheTimeout) {
        return new GenericCache<K, V>(cacheTimeout);
    }
}
