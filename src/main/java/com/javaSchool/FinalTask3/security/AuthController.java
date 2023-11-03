package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.security.dto.LoginRequestBodyDTO;
import com.javaSchool.FinalTask3.security.dto.RegisterRequestBodyDTO;
import org.springframework.http.ResponseEntity;

/**
 * The {@code AuthController} interface is responsible for handling user authentication via a login endpoint.
 * It processes login requests, validates user credentials, and generates JWT tokens upon successful authentication.
 */
public interface AuthController {
    /**
     * Processes user login by validating the provided credentials, and if successful, generates and returns a JWT token.
     * @param loginRequestBodyDto The request body containing the user's email and password.
     * @return ResponseEntity containing the JWT access token upon successful login, or an error response in case of failure.
     */
    ResponseEntity login(LoginRequestBodyDTO loginRequestBodyDto);

    /**
     * Handles user registration by creating a new user account.
     * @param registerRequestBodyDTO The request body containing user registration data.
     * @return ResponseEntity containing the result of the registration process, including authentication information.
     */
    ResponseEntity register(RegisterRequestBodyDTO registerRequestBodyDTO);
}
