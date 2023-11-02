package com.javaSchool.FinalTask3.security.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) that stores the token that is returned after the login.
 */
@Data
public class AuthResultDTO {
    private String accessToken;
    private int id;
    private List<String> role;
}
