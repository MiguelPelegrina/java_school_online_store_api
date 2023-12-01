package com.java_school.final_task.domain.user_role.dto;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user_role.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) of {@link UserRoleEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserRoleDTO {
    private int id;
    private UserDTO user;
    private RoleDTO role;
    private LocalDate assignedDate;
}
