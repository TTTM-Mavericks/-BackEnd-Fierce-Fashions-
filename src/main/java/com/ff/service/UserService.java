package com.ff.service;

import com.ff.entity.UserEntity;

public interface UserService {
    public UserEntity checkLogin(String key, String password);
}
