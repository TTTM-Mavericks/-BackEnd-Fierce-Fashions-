package com.ff.service.impl;

import com.ff.entity.UserEntity;
import com.ff.entity.enum_pkg.Status;
import com.ff.repository.UserRepository;
import com.ff.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

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
