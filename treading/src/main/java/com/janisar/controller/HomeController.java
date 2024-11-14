package com.janisar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public static String home(){
        return "Welcome to Trading Platfrom , how is your day";

    }

    @GetMapping("/api")
    public static String secure(){
        return "Welcome to Trading Platfrom ,Secure";

    }
}
