package com.GymconnectionAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping({"/", "/home", "/status"})
    public String getStatus() {
        return "Application is up and running";
    }
}