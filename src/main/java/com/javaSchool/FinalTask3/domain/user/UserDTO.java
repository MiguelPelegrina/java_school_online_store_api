package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleDTO;
import lombok.Data;

import java.time.LocalDate;

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
    private UserRoleDTO role;
}
