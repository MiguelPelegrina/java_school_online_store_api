package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * Data Transfer Object (DTO) of {@link UserEntity}.
 */
@Data
public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private boolean isActive;
    private String phone;
    private UserAddressDTO address;
    private Set<UserRoleJsonDTO> roles;
}
