package com.task.Supermarket.controller;

import com.task.Supermarket.ApiResponse.WeatherResponse;
import com.task.Supermarket.Services.WeatherServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mall/info")
public class UserWeatherDetails {
    @Autowired
    private WeatherResponse weatherResponse;
    @Autowired
    private WeatherServices weatherServices;

    @GetMapping("/information")
    public ResponseEntity<?> weatherInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherServices.getWeather("Bangalore");
        WeatherResponse city = weatherServices.getWeather("Bangalore");

        if (weatherResponse != null && city != null) {
            String temperature = "Temperature is " + weatherResponse.getCurrent().getTemperature();
            String status = "weather status is " + weatherResponse.getCurrent().getWeatherDescriptions();
            String feelsLike = "weather feels like " + weatherResponse.getCurrent().getFeelsLike();
            String windSpeed = "wind speed is " + weatherResponse.getCurrent().getWindSpeed();
            String windDegree = "Wind degree is " + weatherResponse.getCurrent().getWindDegree();
            String windDirection = "Wind direction is " + weatherResponse.getCurrent().getWindDirection();
            String precipitation = "precipitation is " + weatherResponse.getCurrent().getPrecipitataion();
            String cloudCover = "Cloud cover is " + weatherResponse.getCurrent().getCloudCover();
            String pressure = "Pressure is " + weatherResponse.getCurrent().getPressure();
            String humidity = "Humidity is " + weatherResponse.getCurrent().getHumidity();
            String uvIndex = "uv index is " + weatherResponse.getCurrent().getUvIndex();
            String visibility = "Visibility is" + weatherResponse.getCurrent().getVisibility();
            String country = "country: " + city.getLocation().getCountry();
            String City = "city: " + city.getLocation().getName();
            String region = "Region " + city.getLocation().getRegion();
            String latitude = "latitude " + city.getLocation().getLatitude();
            String longitude = "longitude " + city.getLocation().getLongitude();

            return new ResponseEntity<>("Hi " + authentication.getName() + " " + temperature + " " +
                    status + " " + feelsLike + " " + windSpeed + " " + windDirection
                    + " " + windDegree + " " + precipitation + " " + cloudCover
                    + " " + pressure + " " + humidity + " " + uvIndex
                    + " " + visibility +
                    " " + " " + country + " " + City + " " + region + " " + latitude + " " + longitude
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

//    @GetMapping("/city")
//    public ResponseEntity<?> cityInfo() {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            WeatherResponse city = weatherServices.getWeather("New Delhi");
//            if (city != null) {
//                String country = "country: " + city.getLocation().getCountry();
//                String City = "city: " + city.getLocation().getName();
//                String region = "Region " + city.getLocation().getRegion();
//                String latitude = "latitude " + city.getLocation().getLatitude();
//                String longitude = "longitude " + city.getLocation().getLongitude();
//                return new ResponseEntity<>("Hi " + authentication.getName()
//                        + " " + country + " " + City + " " + region + " " + latitude + " " + longitude
//                        , HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}




