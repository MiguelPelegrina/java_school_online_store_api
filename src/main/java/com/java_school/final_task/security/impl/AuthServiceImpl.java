package com.java_school.final_task.security.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.exception.user.InactiveUserException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.AuthService;
import com.java_school.final_task.security.UserMapper;
import com.java_school.final_task.security.dto.LoginRequestDTO;
import com.java_school.final_task.security.dto.RegisterRequestDTO;
import com.java_school.final_task.utils.StringValues;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Override
    public UserEntity login(LoginRequestDTO loginRequestDTO) {
        UserEntity user = repository.findUserByEmail(loginRequestDTO.getEmail()).orElseThrow(
                () -> new UserDoesNotExistException(String.format(StringValues.USER_DOES_NOT_EXIST, loginRequestDTO.getEmail()))
        );

        if (!user.isActive()) {
            throw new InactiveUserException();
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(StringValues.PASSWORD_NOT_MATCHING);
        }

        return user;
    }

    @Override
    public UserEntity register(RegisterRequestDTO registerRequestDTO) {
        final Optional<UserEntity> userInRepository = repository.findUserByEmail(registerRequestDTO.getEmail());

        if (userInRepository.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity user = userMapper.mapToUserEntity(registerRequestDTO);

        return repository.save(user);
    }
}
