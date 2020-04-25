package com.bjsxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {

    @RequestMapping("/getName")
    public String getName(){
        return "zive";
    }
}