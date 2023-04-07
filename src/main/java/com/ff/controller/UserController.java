package com.ff.controller;

import com.ff.entity.UserEntity;
import com.ff.service.impl.UserServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public ResponseEntity<UserEntity> checkLogin(@PathParam("key") String key, @PathParam("password") String password) {
        UserEntity user = userService.checkLogin(key, password);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
