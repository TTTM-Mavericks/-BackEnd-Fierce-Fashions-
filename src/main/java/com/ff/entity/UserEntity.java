package com.ff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String first_name;
    private String last_name;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] avatar;

    private String address;
    private boolean enabled;
    private String password;
    private RoleEntity role;

    @OneToMany(mappedBy = "users")
    List<CommentProductEntity> comments;

    @OneToMany(mappedBy = "userList")
    List<OrderEntity> orderList;
}