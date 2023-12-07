package com.java_school.final_task.domain.user_role.dto;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.user_role.UserRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object of a role assigned to a user without a circular reference")
public class UserRoleJsonDTO {
    private int id;
    private RoleDTO role;
    private LocalDate assignedDate;
}
