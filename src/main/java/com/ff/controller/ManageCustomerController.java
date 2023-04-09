package com.ff.controller;

import com.ff.entity.UserEntity;
import com.ff.service.AdminService;
import com.ff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manageCustomer")
public class ManageCustomerController {
    @Autowired
    UserService userService;
    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<UserEntity>> getAllCustomer() {
        return new ResponseEntity<>(userService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/banUser/{username}")
    public ResponseEntity<UserEntity> banUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.banUser(username), HttpStatus.OK);
    }

    @GetMapping("/unbanUser/{username}")
    public ResponseEntity<UserEntity> unbanUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.unbanUser(username), HttpStatus.OK);
    }

}
