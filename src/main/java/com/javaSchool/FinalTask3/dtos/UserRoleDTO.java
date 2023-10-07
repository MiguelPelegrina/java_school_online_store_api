package com.javaSchool.FinalTask3.dtos;

import com.javaSchool.FinalTask3.entities.embeddables.UserRoleId;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRoleDTO {
    private UserRoleId id;
    private UserDTO user;
    private RoleDTO role;
    private LocalDate assignedDate;
}
