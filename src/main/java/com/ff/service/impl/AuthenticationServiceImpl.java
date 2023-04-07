package com.ff.service.impl;

import com.ff.entity.RoleEntity;
import com.ff.entity.UserEntity;
import com.ff.repository.UserRepository;
import com.ff.service.AuthenticationService;
import com.ff.service.JwtService;
import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(UserDTO dto) {
        UserEntity user = new UserEntity(dto.getUsername(), dto.getEmail(), dto.getPhone(),
                dto.getFirst_name(), dto.getLast_name(), dto.getAddress(), passwordEncoder.encode(dto.getPassword()), RoleEntity.CUSTOMER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
