package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.mothers.user.UserMother;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;
import com.java_school.final_task.security.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link AuthServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthServiceImpl service;

    private RegisterRequestBodyDTO registerRequestBodyDTO;

    private UserEntity instance;

    @BeforeEach
    public void setUp(){
        // Arrange
        registerRequestBodyDTO = RegisterRequestBodyDTO.builder()
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .password("Password")
                .name("Name")
                .surname("Surname")
                .address(RegisterRequestBodyDTO.Address.builder()
                        .number("1")
                        .street("Street")
                        .postalCode("Code")
                        .build())
                .build();

        instance = UserMother.createUser();
    }

    @Test
    public void UserService_RegisterUser_ReturnUserDTO(){
        // Arrange
        when(repository.findUserByEmail(registerRequestBodyDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapToUserEntity(registerRequestBodyDTO)).thenReturn(instance);
        when(repository.save(instance)).thenReturn(instance);

        // Act
        UserEntity savedInstance = service.register(registerRequestBodyDTO);

        // Assert
        assertThat(savedInstance).isNotNull();
        verify(repository, times(1)).findUserByEmail(registerRequestBodyDTO.getEmail());
        verify(userMapper, times(1)).mapToUserEntity(registerRequestBodyDTO);
        verify(repository, times(1)).save(instance);
        assertEquals(instance, savedInstance);
    }

    @Test
    public void UserService_RegisterUser_ThrowsEmailAlreadyInUseException(){
        // Arrange
        when(repository.findUserByEmail(instance.getEmail())).thenReturn(Optional.of(instance));

        // Act
        assertThrows(EmailAlreadyUsedException.class, () -> service.register(registerRequestBodyDTO));

        // Assert
        verify(repository, times(1)).findUserByEmail((instance.getEmail()));
    }

}
