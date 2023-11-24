package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import mothers.user.UserMother;
import com.java_school.final_task.security.dto.LoginRequestBodyDTO;
import com.java_school.final_task.security.impl.AuthControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link AuthControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthControllerTests {
    @InjectMocks
    private AuthControllerImpl controller;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository repository;

    private LoginRequestBodyDTO loginRequestBody;

    private UserEntity instance;

    private UserDTO instanceDTO;

    @BeforeEach
    public void setUp(){
        // Arrange
        instance = UserMother.createUser();

        loginRequestBody = LoginRequestBodyDTO.builder()
                .email("email@.com")
                .password("Password")
                .build();
    }


}
