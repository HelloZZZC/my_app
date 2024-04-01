package com.heartbeat.myapp.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${spring.application.name}")
    private String application;


    @GetMapping(value = "/health")
    public String health() {
        return "{\"status\":\"UP\",\"appName\":\"" + application + "\"}";
    }
}
