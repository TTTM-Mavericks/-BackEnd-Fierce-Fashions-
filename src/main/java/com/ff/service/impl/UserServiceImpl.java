package com.ff.service.impl;

import com.ff.entity.UserEntity;
import com.ff.entity.enum_pkg.Status;
import com.ff.repository.UserRepository;
import com.ff.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity checkLogin(String key, String password) {
        UserEntity user = userRepository.findByUsernameAndPassword(key, password);
        if (user == null) {
            user = userRepository.findByEmailAndPassword(key, password);
            if (user == null) {
                user = userRepository.findByPhoneAndPassword(key, password);
                return user;
            } else {
                return user;
            }
        } else {
            return user;
        }
    }


    @Override
    @Cacheable(cacheNames = "updateUser")
    public UserEntity updateUser(UserEntity user) {
        if (user != null) {
            userRepository.save(user);
            return user;
        } else
            return null;
    }

    @Override
    @Cacheable(cacheNames = "findAll")
    public List<UserEntity> getAllCustomer() {
        return userRepository.getAllCustomer();
    }

    @Override
    public UserEntity banUser(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if(user != null) {
            user.setStatus_account(Status.INACTIVE);
            userRepository.save(user);
            return user;
        } else
            return null;
    }

    @Override
    public UserEntity unbanUser(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if(user != null) {
            user.setStatus_account(Status.ACTIVE);
            userRepository.save(user);
            return user;
        } else
            return null;
    }
}
