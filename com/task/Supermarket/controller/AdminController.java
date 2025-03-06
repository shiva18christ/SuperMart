package com.task.Supermarket.controller;

import com.task.Supermarket.Cache.AppCache;
import com.task.Supermarket.Services.UserServices;
import com.task.Supermarket.base.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name="Admin APIs",description = "see all users,create new admin,refresh server")
public class AdminController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    @Operation(summary="Get details of all the user registered in app")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userServices.getAll();
        if (all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary="Create a new admin")

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
