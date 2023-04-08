package com.ff.controller;

import com.ff.entity.UserEntity;
import com.ff.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    public List<UserEntity> getAllCustomer(){
        return adminService.getAllCustomer();
    }
}
