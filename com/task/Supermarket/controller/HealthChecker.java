package com.task.Supermarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChecker {
    @GetMapping("/check")
    public String helthcheck() {
        return "okay";
    }

}
