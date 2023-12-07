package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.exception.user.InactiveUserException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.dto.LoginRequestDTO;
import com.java_school.final_task.security.dto.RegisterRequestDTO;
import com.java_school.final_task.security.impl.AuthServiceImpl;
import mothers.user.UserMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link AuthServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTests {
    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthServiceImpl service;

    @Mock
    private PasswordEncoder passwordEncoder;

    private LoginRequestDTO loginRequestDTO;

    private RegisterRequestDTO registerRequestDTO;

    private UserEntity instance;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = UserMother.createUser();

        loginRequestDTO = UserMother.createLoginRequestBodyDTO();

        registerRequestDTO = UserMother.createRegisterRequestBodyDTO();
    }

    @Test
    void UserService_LoginUser_ReturnBadCredentials() {
        // Arrange
        when(repository.findUserByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.ofNullable(instance));
        when(passwordEncoder.matches(loginRequestDTO.getPassword(), instance.getPassword())).thenReturn(false);

        // Act & assert
        assertThrows(BadCredentialsException.class, () -> service.login(loginRequestDTO));
        verify(repository, times(1)).findUserByEmail(loginRequestDTO.getEmail());
        verify(passwordEncoder, times(1)).matches(loginRequestDTO.getPassword(), instance.getPassword());
    }

    @Test
    void UserService_LoginUser_ReturnUserDoesNotExistException() {
        // Arrange
        when(repository.findUserByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.empty());

        // Act & assert
        assertThrows(UserDoesNotExistException.class, () -> service.login(loginRequestDTO));
        verify(repository, times(1)).findUserByEmail(loginRequestDTO.getEmail());
    }

    @Test
    void UserService_LoginUser_ReturnUserDTO() {
        // Arrange
        when(repository.findUserByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.ofNullable(instance));
        when(passwordEncoder.matches(loginRequestDTO.getPassword(), instance.getPassword())).thenReturn(true);

        // Act
        UserEntity user = service.login(loginRequestDTO);

        // Assert
        assertThat(user).isNotNull();
        verify(repository, times(1)).findUserByEmail(loginRequestDTO.getEmail());
        assertEquals(instance, user);
    }

    @Test
    void UserService_LoginUser_ReturnInactiveUser() {
        // Arrange
        instance.setActive(false);
        when(repository.findUserByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.ofNullable(instance));

        // Act & assert
        assertThrows(InactiveUserException.class, () -> service.login(loginRequestDTO));
        verify(repository, times(1)).findUserByEmail(loginRequestDTO.getEmail());
    }

    @Test
    void UserService_RegisterUser_ReturnUserDTO() {
        // Arrange
        when(repository.findUserByEmail(registerRequestDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapToUserEntity(registerRequestDTO)).thenReturn(instance);
        when(repository.save(instance)).thenReturn(instance);

        // Act
        UserEntity savedInstance = service.register(registerRequestDTO);

        // Assert
        assertThat(savedInstance).isNotNull();
        verify(repository, times(1)).findUserByEmail(registerRequestDTO.getEmail());
        verify(userMapper, times(1)).mapToUserEntity(registerRequestDTO);
        verify(repository, times(1)).save(instance);
        assertEquals(instance, savedInstance);
    }

    @Test
    void UserService_RegisterUser_ThrowsEmailAlreadyInUseException() {
        // Arrange
        when(repository.findUserByEmail(instance.getEmail())).thenReturn(Optional.of(instance));

        // Act
        assertThrows(EmailAlreadyUsedException.class, () -> service.register(registerRequestDTO));

        // Assert
        verify(repository, times(1)).findUserByEmail((instance.getEmail()));
    }
}
