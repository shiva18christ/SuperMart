package com.task.Supermarket.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisService {

    @Autowired
    @Qualifier("customRedisTemplate")
    private RedisTemplate radisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object object = radisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(object.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception occurred");
            return null;
        }


    }

    public void set(String key, Object value, Long ttl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(value);
            radisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);


        } catch (Exception e) {
            log.error("Error has occurred");
        }

    }

}
