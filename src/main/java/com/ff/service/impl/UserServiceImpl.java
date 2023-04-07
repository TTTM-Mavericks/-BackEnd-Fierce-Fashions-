package com.ff.service.impl;

import com.ff.entity.UserEntity;
import com.ff.repository.UserRepository;
import com.ff.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserEntity checkLogin(String key, String password) {
        UserEntity user = userRepository.findByUsernameAndPassword(key, password);
        if(user == null){
            user = userRepository.findByEmailAndPassword(key, password);
            if(user == null){
                user = userRepository.findByPhoneAndPassword(key, password);
                if(user == null){
                    return null;
                } else {
                    return user;
                }
            } else {
                return user;
            }
        } else {
            return user;
        }
    }
}
