package com.java_school.final_task.security.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.AuthController;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.security.dto.AuthResultDTO;
import com.java_school.final_task.security.dto.LoginRequestBodyDTO;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;
import com.java_school.final_task.utils.StringValues;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Override
    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestBodyDTO loginRequestBodyDto)  {
        try {
            // Get the user
            UserEntity user = repository.findUserByEmail(loginRequestBodyDto.getEmail()).orElseThrow(
                    () -> new UserDoesNotExistException(String.format(StringValues.INSTANCE_NOT_FOUND, loginRequestBodyDto.getEmail()))
            );

            if(!user.isActive()){
                return ResponseEntity.status(401).body(StringValues.INACTIVE_USER);
            }

            if(!passwordEncoder.matches(loginRequestBodyDto.getPassword(), user.getPassword())){
                return ResponseEntity.status(401).body(StringValues.PASSWORD_NOT_MATCHING);
            }

            return ResponseEntity.ok(this.generateAuthResultDTO(user));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StringValues.INVALID_CREDENTIALS);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    @RequestMapping("/register")
    @Override
    public ResponseEntity<?> register(@RequestBody RegisterRequestBodyDTO registerRequestBodyDTO){
        try{
            return ResponseEntity.ok(this.generateAuthResultDTO(service.register(registerRequestBodyDTO)));
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Generates an AuthResultDTO object based on a UserEntity, including an access token, user ID, and user roles.
     * @param user The UserEntity for which to generate the AuthResultDTO.
     * @return An AuthResultDTO containing the access token, user ID, and user roles.
     */
    private AuthResultDTO generateAuthResultDTO(UserEntity user){
        AuthResultDTO resultDto = new AuthResultDTO();
        resultDto.setAccessToken(jwtUtil.createToken(user));
        return resultDto;
    }
}
