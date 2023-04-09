package com.ff.service;

import com.ff.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity checkLogin(String key, String password);
    UserEntity updateUser(UserEntity user);

    List<UserEntity> getAllCustomer();
    UserEntity banUser(String username);
    UserEntity unbanUser(String username);
}
