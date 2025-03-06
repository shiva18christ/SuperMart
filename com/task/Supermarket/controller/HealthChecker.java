package com.task.Supermarket.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Health-check API",description = "To see if the server is responding")
public class HealthChecker {
    @GetMapping("/check")
    public String helthcheck() {
        return "okay";
    }

}
