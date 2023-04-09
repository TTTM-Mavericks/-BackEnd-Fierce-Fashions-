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
            UserEntity oldUser = userRepository.findById(user.getId()).get();
            if (user.getUsername() == null
                    || user.getUsername().isBlank()
                    || user.getUsername().isEmpty()) {
                user.setUsername(oldUser.getUsername());
            }
            if (user.getPassword() == null
                    || user.getPassword().isBlank()
                    || user.getPassword().isEmpty()) {
                user.setPassword(oldUser.getPassword());
            }
            if (user.getAddress() == null
                    || user.getAddress().isBlank()
                    || user.getAddress().isEmpty()) {
                user.setAddress(oldUser.getAddress());
            }
            if (user.getEmail() == null
                    || user.getEmail().isBlank()
                    || user.getEmail().isEmpty()) {
                user.setEmail(oldUser.getEmail());
            }
            if (user.getFirst_name() == null
                    || user.getFirst_name().isBlank()
                    || user.getFirst_name().isEmpty()) {
                user.setFirst_name(oldUser.getFirst_name());
            }
            if (user.getLast_name() == null
                    || user.getLast_name().isBlank()
                    || user.getLast_name().isEmpty()) {
                user.setLast_name(oldUser.getLast_name());
            }
            if (user.getPhone() == null
                    || user.getPhone().isBlank()
                    || user.getPhone().isEmpty()) {
                user.setPhone(oldUser.getPhone());
            }
            user.setRole(oldUser.getRole());
            user.setStatus_account(oldUser.getStatus_account());
            return userRepository.save(user);
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
        if (user != null) {
            user.setStatus_account(Status.INACTIVE);
            userRepository.save(user);
            return user;
        } else
            return null;
    }

    @Override
    public UserEntity unbanUser(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if (user != null) {
            user.setStatus_account(Status.ACTIVE);
            userRepository.save(user);
            return user;
        } else
            return null;
    }
}
