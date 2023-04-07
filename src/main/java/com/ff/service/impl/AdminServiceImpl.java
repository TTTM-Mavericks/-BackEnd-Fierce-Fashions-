package com.ff.service.impl;

import com.ff.entity.UserEntity;
import com.ff.repository.UserRepository;
import com.ff.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllCustomer() {
        return userRepository.getAllCustomer();
    }
}
