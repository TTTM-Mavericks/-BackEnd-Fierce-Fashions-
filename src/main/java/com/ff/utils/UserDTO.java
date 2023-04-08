package com.ff.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String email;

    private String phone;

    private String first_name;

    private String last_name;

    private String address;

    private String password;

    private String role;
}
