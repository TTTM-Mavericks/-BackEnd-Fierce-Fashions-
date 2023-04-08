package com.ff.service.impl;

import com.ff.entity.enum_pkg.RoleEntity;
import com.ff.entity.UserEntity;
import com.ff.entity.enum_pkg.Status;
import com.ff.exception.CustomException;
import com.ff.repository.UserRepository;
import com.ff.service.AuthenticationService;
import com.ff.service.JwtService;
import com.ff.service.ServiceMsg.ConstantMessage;
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
        RoleEntity role = null;
        switch (dto.getRole().toUpperCase())
        {
            case "CUSTOMER":
                role = RoleEntity.CUSTOMER;
                break;
            case "MODERATOR":
                role = RoleEntity.MODERATOR;
                break;
            case "CONTENT":
                role = RoleEntity.CONTENT;
                break;
            case "PRODUCT_MANAGER":
                role = RoleEntity.PRODUCT_MANAGER;
                break;
            case "SHIPPER":
                role = RoleEntity.SHIPPER;
                break;
            case "ADMIN":
                role = RoleEntity.ADMIN;
                break;
        }
        if (userRepository.findUserByUsername(dto.getUsername()) != null)
        {
            logger.warn(ConstantMessage.USERNAME_IS_EXIST);
            throw new CustomException(ConstantMessage.USERNAME_IS_EXIST + "");
        }
        if (userRepository.findUserByPhone(dto.getPhone()) != null)
        {
            logger.warn(ConstantMessage.PHONE_IS_EXIST);
            throw new CustomException(ConstantMessage.PHONE_IS_EXIST + "");
        }
        if (userRepository.findUserByEmail(dto.getEmail()) != null)
        {
            logger.warn(ConstantMessage.EMAIL_IS_EXIST);
            throw new CustomException(ConstantMessage.EMAIL_IS_EXIST + "");
        }
        UserEntity user = new UserEntity(dto.getUsername(), dto.getEmail(), dto.getPhone(),
                dto.getFirst_name(), dto.getLast_name(), dto.getAddress(), passwordEncoder.encode(dto.getPassword()), role);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        logger.info("Create new Username: {} Successfully", dto.getUsername());
        return new ResponseEntity<>(AuthenticationResponse.builder().token(jwtToken).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest dto) throws CustomException {
        UserEntity user = userRepository.findUserByUsernameAndStatus(dto.getUsername(), Status.ACTIVE + "");
        if(user == null)
        {
            logger.warn(ConstantMessage.INVALID_USERNAME_OR_PASSWORD);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);

        logger.info("Login " + ConstantMessage.SUCCESS);

        return new ResponseEntity<>(AuthenticationResponse.builder().token(jwtToken).build(), HttpStatus.OK);
    }
}
