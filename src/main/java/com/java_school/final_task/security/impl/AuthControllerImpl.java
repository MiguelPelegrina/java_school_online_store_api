package com.java_school.final_task.security.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.security.AuthController;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.security.dto.AuthResultDTO;
import com.java_school.final_task.security.dto.LoginRequestBodyDTO;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code AuthController} class is responsible for handling user authentication via a login endpoint.
 * It processes login requests, validates user credentials, and generates JWT tokens upon successful authentication.
 */
@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthControllerImpl implements AuthController {
    // Fields
    private final AuthServiceImpl service;
    private final JwtUtil jwtUtil;

    @Override
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestBodyDTO loginRequestBodyDTO) {
        return ResponseEntity.ok(this.generateAuthResultDTO(service.login(loginRequestBodyDTO)));
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestBodyDTO registerRequestBodyDTO) {
        return ResponseEntity.ok(this.generateAuthResultDTO(service.register(registerRequestBodyDTO)));
    }

    /**
     * Generates an AuthResultDTO object based on a UserEntity, including an access token, user ID, and user roles.
     *
     * @param user The UserEntity for which to generate the AuthResultDTO.
     * @return An AuthResultDTO containing the access token, user ID, and user roles.
     */
    private AuthResultDTO generateAuthResultDTO(UserEntity user) {
        AuthResultDTO resultDto = new AuthResultDTO();
        resultDto.setAccessToken(jwtUtil.createToken(user));
        return resultDto;
    }
}
