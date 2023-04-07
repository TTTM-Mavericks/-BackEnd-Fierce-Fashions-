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
    Optional<UserEntity> findByUsername(String username);
}
