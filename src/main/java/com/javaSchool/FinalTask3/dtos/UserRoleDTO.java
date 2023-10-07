package com.javaSchool.FinalTask3.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRoleDTO {
    private int id;
    private UserDTO user;
    private RoleDTO role;
    private LocalDate assignedDate;
}
