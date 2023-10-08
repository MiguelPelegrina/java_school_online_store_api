package com.javaSchool.FinalTask3.dtos;

import com.javaSchool.FinalTask3.entities.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class RoleDTO {
    private String name;
    private Set<UserRole> roles;
}
