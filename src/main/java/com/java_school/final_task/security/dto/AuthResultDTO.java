package com.java_school.final_task.security.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) that stores the token that is returned after the login.
 */
@Data
public class AuthResultDTO {
    private String accessToken;
}