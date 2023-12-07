package com.java_school.final_task.domain.user.dto;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.user_address.UserAddressDTO;
import com.java_school.final_task.domain.user_role.dto.UserRoleJsonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object of a user")
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
