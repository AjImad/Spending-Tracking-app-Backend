package com.apptracker.spendingtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class Demo {

    @GetMapping(path = "/hello")
    public String sayHello(){
        return "Hello world!";
    }
}
