package com.ff.service;

import com.ff.utils.AuthenticationRequest;
import com.ff.utils.AuthenticationResponse;
import com.ff.utils.UserDTO;

public interface AuthenticationService {
    AuthenticationResponse register(UserDTO dto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
