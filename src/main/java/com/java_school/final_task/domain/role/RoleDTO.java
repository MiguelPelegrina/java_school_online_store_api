package com.java_school.final_task.domain.role;

import com.java_school.final_task.domain.userRole.UserRoleEntity;
import lombok.Data;

import java.util.Set;

/**
 * Data Transfer Object (DTO) of {@link RoleEntity}.
 */
@Data
public class RoleDTO {
    private String name;
    private Set<UserRoleEntity> roles;
}
