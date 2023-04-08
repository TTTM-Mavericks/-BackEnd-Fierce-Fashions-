package com.ff.service;

import com.ff.entity.UserEntity;

import java.util.List;

public interface AdminService {
    List<UserEntity> getAllCustomer();
    UserEntity banUser(String username);
    UserEntity unbanUser(String username);
}
