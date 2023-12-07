package com.java_school.final_task.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Data Transfer Object (DTO) that stores the token that is returned after the login.
 */
@Data
@Schema(description = "Data Transfer Object of a authentication result after logging in")
public class AuthResultDTO {
    private String accessToken;
}
