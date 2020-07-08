package com.java.analytics.controller;


import com.java.analytics.service.AnalyticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
@RequestMapping("/")
@Log4j2
public class AnalyticController {

    @Autowired
    AnalyticService service;

    @GetMapping
    public void generate() throws IOException {

        try {
            service.run();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }




    }
}
