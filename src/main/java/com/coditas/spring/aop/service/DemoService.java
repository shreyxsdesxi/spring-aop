package com.coditas.spring.aop.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String demoService() throws InterruptedException {
        Thread.sleep(2000);
        return "Demo Service Impl";
    }
}
