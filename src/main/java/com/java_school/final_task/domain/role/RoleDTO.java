package com.java_school.final_task.domain.role;

import com.java_school.final_task.domain.user_role.UserRoleEntity;
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
public class RoleDTO {
    private String name;
    private Set<UserRoleEntity> roles;
}
