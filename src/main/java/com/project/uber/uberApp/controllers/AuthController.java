package com.project.uber.uberApp.controllers;

import com.project.uber.uberApp.dto.*;
import com.project.uber.uberApp.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        return new ResponseEntity<>(authService.signup(signUpDTO), HttpStatus.CREATED);
    }

    //@Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDTO onboardDriverDTO){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId,onboardDriverDTO.getVehicleId()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                           HttpServletResponse httpServletResponse,
                                           HttpServletRequest httpServletRequest){
        String[] tokens = authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());

        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        String accessToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }
}
