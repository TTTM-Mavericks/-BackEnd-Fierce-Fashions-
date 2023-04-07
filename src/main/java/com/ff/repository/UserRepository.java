package com.ff.repository;

import com.ff.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneAndPassword(String phone, String password);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByUsernameAndPassword(String username, String password);

    @Query(
            value = "select * from users where username = ?1", nativeQuery = true
    )
    UserEntity findUserByUsername(String username);

    @Query(
            value = "select * from users where username = ?1 and is_activated = ?2", nativeQuery = true
    )
    UserEntity findUserByUsernameAndStatus(String username, boolean status);
}
