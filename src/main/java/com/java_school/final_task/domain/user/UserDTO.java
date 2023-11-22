package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * Data Transfer Object (DTO) of {@link UserEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
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
