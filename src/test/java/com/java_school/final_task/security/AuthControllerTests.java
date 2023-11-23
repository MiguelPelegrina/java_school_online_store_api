package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.domain.user.impl.UserServiceImpl;
import com.java_school.final_task.domain.user.userAddress.UserAddressRepository;
import com.java_school.final_task.domain.userRole.UserRoleRepository;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.mothers.user.UserMother;
import com.java_school.final_task.security.dto.AuthResultDTO;
import com.java_school.final_task.security.dto.LoginRequestBodyDTO;
import com.java_school.final_task.security.impl.AuthControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
