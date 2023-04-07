package com.ff.repository;

import com.ff.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneAndPassword(String phone, String password);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByUsernameAndPassword(String username, String password);
}
