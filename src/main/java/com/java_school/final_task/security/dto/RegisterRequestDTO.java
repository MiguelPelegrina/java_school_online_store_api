package com.java_school.final_task.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) that stores the information for a login.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a register request")
public class RegisterRequestDTO {
    private Address address;
    private LocalDate dateOfBirth;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String surname;

    @AllArgsConstructor
    @Builder
    @Data
    @NoArgsConstructor
    @Schema(description = "Simplified address of a register request")
    public static class Address {
        private String number;
        private String postalCode;
        private String street;
    }
}
