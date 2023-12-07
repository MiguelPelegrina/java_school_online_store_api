package com.java_school.final_task.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.user.EmailAlreadyUsedException;
import com.java_school.final_task.exception.user.InactiveUserException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.dto.LoginRequestDTO;
import com.java_school.final_task.security.dto.RegisterRequestDTO;
import com.java_school.final_task.security.impl.AuthControllerImpl;
import com.java_school.final_task.security.impl.AuthServiceImpl;
import com.java_school.final_task.utils.StringValues;
import mothers.user.UserMother;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link AuthControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthControllerTests {
    @MockBean
    private AuthServiceImpl service;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserRepository repository;

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
    void AuthController_LoginUser_ReturnsUserDTO() throws Exception {
        // Arrange
        when(service.login(loginRequestDTO)).thenReturn(instance);
        when(jwtUtil.createToken(instance)).thenReturn("mockedAccessToken");

        // Act
        ResultActions result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", CoreMatchers.is("mockedAccessToken")));
    }

    @Test
    void AuthController_RegisterUser_ReturnsUserDTO() throws Exception {
        // Arrange
        when(service.register(registerRequestDTO)).thenReturn(instance);
        when(jwtUtil.createToken(instance)).thenReturn("mockedAccessToken");

        // Act
        ResultActions result = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", CoreMatchers.is("mockedAccessToken")));
    }

    @Test
    void AuthController_RegisterUser_HandleEmailAlreadyExistsException() throws Exception {
        // Assert
        when(service.register(registerRequestDTO)).thenThrow(new EmailAlreadyUsedException());

        // Act & assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(EmailAlreadyUsedException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(StringValues.EMAIL_ALREADY_IN_USE, Objects.requireNonNull(result.getResolvedException()).getMessage()));

        verify(service, times(1)).register(registerRequestDTO);
    }

    @Test
    void AuthController_LoginUser_HandleInactiveUserException() throws Exception {
        // Arrange
        when(service.login(loginRequestDTO)).thenThrow(new InactiveUserException());

        // Act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(InactiveUserException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(StringValues.INACTIVE_USER, Objects.requireNonNull(result.getResolvedException()).getMessage()));

        verify(service, times(1)).login(loginRequestDTO);
    }

    @Test
    void AuthController_LoginUser_HandleUserDoesNotExistException() throws Exception {
        // Arrange
        when(service.login(loginRequestDTO)).thenThrow(new UserDoesNotExistException(instance.getEmail()));

        // Act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserDoesNotExistException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(
                        String.format(StringValues.USER_DOES_NOT_EXIST, instance.getEmail()),
                        Objects.requireNonNull(result.getResolvedException()).getMessage())
                );

        verify(service, times(1)).login(loginRequestDTO);
    }

    @Test
    void AuthController_LoginUser_HandleBadCredentialsException() throws Exception {
        // Arrange
        when(service.login(loginRequestDTO)).thenThrow(new BadCredentialsException(StringValues.PASSWORD_NOT_MATCHING));

        // Act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isConflict())
                .andExpect(result -> assertInstanceOf(BadCredentialsException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(
                        StringValues.PASSWORD_NOT_MATCHING,
                        Objects.requireNonNull(result.getResolvedException()).getMessage())
                );

        verify(service, times(1)).login(loginRequestDTO);
    }
}
