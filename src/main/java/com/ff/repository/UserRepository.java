package com.ff.repository;

import com.ff.entity.UserEntity;
import com.ff.entity.enum_pkg.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneAndPassword(String phone, String password);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByUsernameAndPassword(String username, String password);

    @Query(
            value = "select * from users where username = ?1", nativeQuery = true
    )
    UserEntity findUserByUsername(String username);

    @Query(
            value = "select * from users where email = ?1", nativeQuery = true
    )
    UserEntity findUserByEmail(String email);

    @Query(
            value = "select * from users where phone = ?1", nativeQuery = true
    )
    UserEntity findUserByPhone(String phone);

    @Query(
            value = "select * from users where username = ?1 and status_account = ?2", nativeQuery = true
    )
    UserEntity findUserByUsernameAndStatus(String username, String status);

    @Query(
            value = " select * from users where role = 'CUSTOMER'", nativeQuery = true
    )
    List<UserEntity> getAllCustomer();
}
