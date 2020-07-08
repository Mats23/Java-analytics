package com.java.analytics.controller;

import com.java.analytics.service.AnalyticService;
import com.java.analytics.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    @Autowired
    FileService service;

    @Autowired
    AnalyticService analyticService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody byte[] file) throws IOException {
        try {
            boolean filesaved =  service.save(file);
            if (filesaved) {
                this.analyticService.run();
            }
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
