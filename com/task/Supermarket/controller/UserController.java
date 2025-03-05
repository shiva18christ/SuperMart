package com.task.Supermarket.controller;

import com.task.Supermarket.ApiResponse.WeatherResponse;
import com.task.Supermarket.Services.UserServices;
import com.task.Supermarket.Services.WeatherServices;
import com.task.Supermarket.base.User;
import com.task.Supermarket.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepo repo;
    @Autowired
    private WeatherServices weatherServices;

    @GetMapping
    private ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByuserName(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PostMapping
    private ResponseEntity<User> createUsers(@RequestBody User user) {
        try {
            user.setDate(LocalDateTime.now());
            userServices.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping
    public ResponseEntity<?> updateUserByUserName(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User existing_user = userServices.findBYUserName(userName);

        existing_user.setUserName(user.getUserName());
        existing_user.setPassword(user.getPassword());
        existing_user.setEmail(user.getEmail());
        existing_user.setWeatherAnalysis(user.isWeatherAnalysis());
        existing_user.setDate(LocalDateTime.now());
        userServices.saveNewUser(existing_user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByuserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/greeting")
    public ResponseEntity<?>greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse=weatherServices.getWeather("Bangalore");
        if(weatherResponse!=null){
            String temperature="Temperature is "+weatherResponse.getCurrent().getTemperature();
            String status="weather status is "+weatherResponse.getCurrent().getWeatherDescriptions();
            String feelsLike="weather feels like "+weatherResponse.getCurrent().getFeelsLike();
            return new ResponseEntity<>("Hi "+authentication.getName()+" "+temperature+" "+
                    status+" "+feelsLike,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}


