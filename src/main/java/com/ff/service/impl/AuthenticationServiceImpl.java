package com.ff.service.impl;

import com.ff.entity.RoleEntity;
import com.ff.entity.UserEntity;
import com.ff.exception.CustomException;
import com.ff.repository.UserRepository;
import com.ff.service.AuthenticationService;
import com.ff.service.JwtService;
import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);
    @Override
    public ResponseEntity<AuthenticationResponse> register(UserDTO dto) throws CustomException
    {
        if (userRepository.findUserByUsername(dto.getUsername()) != null)
        {
            logger.warn("Username already existed");
            throw new CustomException("Username already existed");
        }
        UserEntity user = new UserEntity(dto.getUsername(), dto.getEmail(), dto.getPhone(),
                dto.getFirst_name(), dto.getLast_name(), dto.getAddress(), passwordEncoder.encode(dto.getPassword()), RoleEntity.CUSTOMER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        logger.info("Create new Username: {} Successfully", dto.getUsername());
        return new ResponseEntity<>(AuthenticationResponse.builder().token(jwtToken).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest dto) throws CustomException {
        UserEntity user = userRepository.findUserByUsernameAndStatus(dto.getUsername(), true);
        if(user == null)
        {
            logger.warn("Invalid Username or password");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);

        logger.info("Login Successfully");

        return new ResponseEntity<>(AuthenticationResponse.builder().token(jwtToken).build(), HttpStatus.OK);
    }
}
