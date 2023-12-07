package com.java_school.final_task.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that stores the information for a login.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a login request")
public class LoginRequestDTO {
    private String email;
    private String password;
}
