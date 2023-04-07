package com.ff.controller;

import com.ff.service.AuthenticationService;
import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(authenticationService.register(userDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest userDTO)
    {
        return ResponseEntity.ok(authenticationService.authenticate(userDTO));
    }

}
