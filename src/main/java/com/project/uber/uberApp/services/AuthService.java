package com.project.uber.uberApp.services;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.SignUpDTO;
import com.project.uber.uberApp.dto.UserDTO;

public interface AuthService {

    String[] login(String email, String password);

    UserDTO signup(SignUpDTO signUpDTO);

    DriverDTO onBoardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
