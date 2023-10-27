package com.javaSchool.FinalTask3.security;

import lombok.Data;

@Data
public class LoginRequestBodyDTO {
    private String email;
    private String password;
}
