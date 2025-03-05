package com.task.Supermarket.controller;

import com.task.Supermarket.Cache.AppCache;
import com.task.Supermarket.Services.UserServices;
import com.task.Supermarket.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userServices.getAll();
        if (all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody User body) {
        try {
            body.setDate(LocalDateTime.now());
            userServices.saveNewAdmin(body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshServer() {
        appCache.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
