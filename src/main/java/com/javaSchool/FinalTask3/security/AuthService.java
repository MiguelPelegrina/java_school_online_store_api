package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.security.dto.RegisterRequestBodyDTO;

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
