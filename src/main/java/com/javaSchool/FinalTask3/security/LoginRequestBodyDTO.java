package com.javaSchool.FinalTask3.security;

import lombok.Data;

/**
 * Data Transfer Object (DTO) that stores the information for a login.
 */
@Data
public class LoginRequestBodyDTO {
    private String email;
    private String password;
}
