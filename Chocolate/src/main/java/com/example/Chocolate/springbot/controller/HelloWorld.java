package com.example.Chocolate.springbot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String getHello(){
        return "Hello";
    }
}
