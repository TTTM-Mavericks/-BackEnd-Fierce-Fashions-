package com.ff.controller;

import com.ff.exception.CustomException;
import com.ff.service.AuthenticationService;
import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "register new account for all role")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO) throws CustomException
    {
        return authenticationService.register(userDTO);
    }

    @Operation(summary = "login account for all role")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest userDTO) throws CustomException
    {
        return authenticationService.login(userDTO);
    }

}
