package com.ff.service;

import com.ff.entity.UserEntity;

public interface UserService {
    UserEntity checkLogin(String key, String password);
    UserEntity updateUser(UserEntity user);
}
