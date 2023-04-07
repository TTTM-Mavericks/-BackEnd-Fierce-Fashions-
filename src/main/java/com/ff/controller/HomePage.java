package com.ff.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage")
@Slf4j
public class HomePage {
    @GetMapping
    public ResponseEntity<String> homePage()
    {
        log.info("Welcome to home page");
        return new ResponseEntity<>("Welcome to Home Page", HttpStatus.OK);
    }
}
