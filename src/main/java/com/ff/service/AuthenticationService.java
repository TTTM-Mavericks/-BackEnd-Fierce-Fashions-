package com.ff.service;

import com.ff.exception.CustomException;
import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> register(UserDTO dto) throws CustomException;

    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest dto) throws CustomException;
}
