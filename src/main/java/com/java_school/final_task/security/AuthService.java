package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.security.dto.LoginRequestDTO;
import com.java_school.final_task.security.dto.RegisterRequestDTO;

/**
 * The {@code AuthService} interface is responsible for user registration and related authentication operations.
 */
public interface AuthService {
    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginRequestDTO The DTO (Data Transfer Object) containing user login credentials.
     *                        It should include the user's email and password.
     * @return The authenticated UserEntity if login is successful.
     */
    UserEntity login(LoginRequestDTO loginRequestDTO);

    /**
     * Registers a new user based on the provided registration data.
     *
     * @param registerRequestDTO The registration data, including user details.
     * @return The registered user entity.
     */
    UserEntity register(RegisterRequestDTO registerRequestDTO);
}
