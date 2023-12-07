package com.java_school.final_task.domain.role;

import com.java_school.final_task.domain.user_role.UserRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Data Transfer Object (DTO) of {@link RoleEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a role")
public class RoleDTO {
    private String name;
    private Set<UserRoleEntity> roles;
}
