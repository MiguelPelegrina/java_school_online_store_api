package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleJsonDTO;
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
    // TODO Not sure if right
    private UserAddressDTO address;
    // TODO Turning this into a set breaks the any user request
    private Set<UserRoleJsonDTO> roles;
}
