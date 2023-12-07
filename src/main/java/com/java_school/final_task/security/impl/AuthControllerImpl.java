package com.java_school.final_task.security.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.security.AuthController;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.security.dto.AuthResultDTO;
import com.java_school.final_task.security.dto.LoginRequestDTO;
import com.java_school.final_task.security.dto.RegisterRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code AuthController} class is responsible for handling user authentication via a login endpoint.
 * It processes login requests, validates user credentials, and generates JWT tokens upon successful authentication.
 */
@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthControllerImpl implements AuthController {
    // Fields
    private final AuthServiceImpl service;
    private final JwtUtil jwtUtil;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in the user",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "404", description = "User is not registered", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bad credentials", content = @Content)
    })
    @Operation(summary = "Logs in a user")
    @Override
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login request", required = true,
                    content = @Content(schema = @Schema(implementation = LoginRequestDTO.class)))
            @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(this.generateAuthResultDTO(service.login(loginRequestDTO)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registered in the user",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "400", description = "Email is already being used", content = @Content)
    })
    @Operation(summary = "Registers a user")
    @Override
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Register request", required = true,
                    content = @Content(schema = @Schema(implementation = RegisterRequestDTO.class)))
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(this.generateAuthResultDTO(service.register(registerRequestDTO)));
    }

    /**
     * Generates an AuthResultDTO object based on a UserEntity, including an access token, user ID, and user roles.
     *
     * @param user The UserEntity for which to generate the AuthResultDTO.
     * @return An AuthResultDTO containing the access token, user ID, and user roles.
     */
    private AuthResultDTO generateAuthResultDTO(UserEntity user) {
        AuthResultDTO resultDto = new AuthResultDTO();
        resultDto.setAccessToken(jwtUtil.createToken(user));
        return resultDto;
    }
}
