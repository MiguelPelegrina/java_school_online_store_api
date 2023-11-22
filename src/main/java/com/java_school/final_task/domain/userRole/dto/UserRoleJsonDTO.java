package com.java_school.final_task.domain.userRole.dto;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
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
public class UserRoleJsonDTO {
    private int id;
    private RoleDTO role;
    private LocalDate assignedDate;
}
