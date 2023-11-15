package com.javaSchool.FinalTask3.domain.userRole.dto;

import com.javaSchool.FinalTask3.domain.role.RoleDTO;
import com.javaSchool.FinalTask3.domain.user.UserDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) of {@link UserRoleEntity}.
 */
@Data
public class UserRoleDTO {
    private int id;
    private UserDTO user;
    private RoleDTO role;
    private LocalDate assignedDate;
}
