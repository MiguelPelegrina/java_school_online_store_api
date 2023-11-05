package com.javaSchool.FinalTask3.security.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) that stores the token that is returned after the login.
 */
@Data
public class AuthResultDTO {
    private String accessToken;
    private int id;
}
