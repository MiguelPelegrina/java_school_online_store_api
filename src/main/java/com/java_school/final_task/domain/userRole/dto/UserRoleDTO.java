package com.java_school.final_task.domain.userRole.dto;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
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
