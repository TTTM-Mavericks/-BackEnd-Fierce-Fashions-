package com.ff.entity;

import com.ff.entity.enum_pkg.RoleEntity;
import com.ff.entity.enum_pkg.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
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

//    @Lob
//    @Column(columnDefinition = "bytea")
//    private byte[] avatar;

    private String address;

    @Enumerated(EnumType.STRING)
    private Status status_account;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEntity role;

    @OneToMany(mappedBy = "users")
    List<CommentProductEntity> comments;

    @OneToMany(mappedBy = "userList")
    List<OrderEntity> orderList;

    public UserEntity(String username, String email, String phone, String first_name, String last_name, String address, String password, RoleEntity role) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.password = password;
        this.role = role;
        this.status_account = Status.ACTIVE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}