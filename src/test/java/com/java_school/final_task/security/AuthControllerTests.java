package com.java_school.final_task.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.security.dto.LoginRequestBodyDTO;
import com.java_school.final_task.security.dto.RegisterRequestBodyDTO;
import com.java_school.final_task.security.impl.AuthControllerImpl;
import com.java_school.final_task.security.impl.AuthServiceImpl;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link AuthControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthControllerTests {
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

    private LoginRequestBodyDTO loginRequestBodyDTO;

    private RegisterRequestBodyDTO registerRequestBodyDTO;

    private UserEntity instance;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = UserMother.createUser();

        loginRequestBodyDTO = UserMother.createLoginRequestBodyDTO();

        registerRequestBodyDTO = UserMother.createRegisterRequestBodyDTO();
    }

    @Test
    void UserController_LoginUser_ReturnsUserDTO() throws Exception {
        // Arrange
        when(service.login(loginRequestBodyDTO)).thenReturn(instance);
        when(jwtUtil.createToken(instance)).thenReturn("mockedAccessToken");

        // Act
        ResultActions result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestBodyDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", CoreMatchers.is("mockedAccessToken")));
    }

    @Test
    void UserController_RegisterUser_ReturnsUserDTO() throws Exception {
        // Arrange
        when(service.register(registerRequestBodyDTO)).thenReturn(instance);
        when(jwtUtil.createToken(instance)).thenReturn("mockedAccessToken");

        // Act
        ResultActions result = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestBodyDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", CoreMatchers.is("mockedAccessToken")));
    }
}
