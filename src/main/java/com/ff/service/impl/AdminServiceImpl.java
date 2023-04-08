package com.ff.service.impl;

import com.ff.entity.UserEntity;
import com.ff.repository.UserRepository;
import com.ff.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllCustomer() {
        return userRepository.getAllCustomer();
    }

    @Override
    public UserEntity banUser(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if(user != null) {
            user.set_activated(false);
            userRepository.save(user);
            return user;
        } else
            return null;
    }

    @Override
    public UserEntity unbanUser(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if(user != null) {
            user.set_activated(true);
            userRepository.save(user);
            return user;
        } else
            return null;
    }
}
