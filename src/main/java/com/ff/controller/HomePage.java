package com.ff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage")
public class HomePage {
    @GetMapping
    public ResponseEntity<String> homePage()
    {
        return new ResponseEntity<>("Welcome to Home Page", HttpStatus.OK);
    }
}
