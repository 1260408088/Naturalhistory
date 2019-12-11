package com.king.naturalhistory.naturalhistory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helloworld {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
