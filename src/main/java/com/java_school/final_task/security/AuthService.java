package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;

/**
 * The {@code AuthService} interface is responsible for user registration and related authentication operations.
 */
public interface AuthService {
    /**
     * Registers a new user based on the provided registration data.
     * @param registerRequestBodyDTO The registration data, including user details.
     * @return The registered user entity.
     */
    UserEntity register(RegisterRequestBodyDTO registerRequestBodyDTO);
}
