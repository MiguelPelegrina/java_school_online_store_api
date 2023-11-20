package com.java_school.final_task.security.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.security.AuthService;
import com.java_school.final_task.security.UserMapper;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * The {@code AuthServiceImpl} class is responsible for user registration and related authentication operations.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    // Fields
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Registers a new user based on the provided registration data.
     * @param registerRequestBodyDTO The registration data, including user details.
     * @return The registered user entity.
     * @throws EmailAlreadyUsedException if user's email is already being used.
     */
    @Override
    public UserEntity register(RegisterRequestBodyDTO registerRequestBodyDTO) throws EmailAlreadyUsedException {
        // Check if a user with the same email already exists in the repository.
        final Optional<UserEntity> userInRepository = userRepository.findUserByEmail(registerRequestBodyDTO.getEmail());
        if (userInRepository.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = userMapper.mapToUserEntity(registerRequestBodyDTO);

        return userRepository.save(user);
    }
}
