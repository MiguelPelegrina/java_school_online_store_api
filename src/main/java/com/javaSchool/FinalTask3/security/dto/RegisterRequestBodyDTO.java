package com.javaSchool.FinalTask3.security.dto;

import lombok.Data;

import java.time.LocalDate;

/**
* Data Transfer Object (DTO) that stores the information for a login.
*/
@Data
public class RegisterRequestBodyDTO {
    private LocalDate dateOfBirth;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String surname;
}
