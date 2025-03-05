package com.task.Supermarket.Services;

import com.task.Supermarket.ApiResponse.WeatherResponse;
import com.task.Supermarket.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherServices {
    @Value("${Weather.api.key}")
    private String apiKey;
    //    private static final String API="https://api.weatherstack.com/current?access_key=API&query=CITY";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    public AppCache appCache;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get(city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalApi = appCache.APP_CACHE.get("weather_api").replace("CITY", city).replace("API", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set(city, body, 300l);
            }
            return body;

        }


    }
}
