package com.coditas.spring.aop.controller;

import com.coditas.spring.aop.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/demo")
    public String demo() throws InterruptedException {
        return demoService.demoService();
    }
}
