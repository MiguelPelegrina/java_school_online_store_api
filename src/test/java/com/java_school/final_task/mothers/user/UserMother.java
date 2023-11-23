package com.java_school.final_task.mothers.user;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
import com.java_school.final_task.mothers.user.address.UserAddressMother;

import java.time.LocalDate;
import java.util.Set;

// TODO Randomize values
public class UserMother {
    public static UserEntity createUser(){
        return UserEntity.builder()
                .id(4)
                .active(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .password("Password")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleEntity.builder()
                        .assignedDate(LocalDate.now())
                        .id(1)
                        .role(RoleEntity.builder()
                                .name("ADMIN")
                                .build())
                        .build()))
                .address(UserAddressMother.createUserAddress())
                .build();
    }

    public static UserDTO createUserDTO(){
        return UserDTO.builder()
                .id(4)
                .isActive(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleJsonDTO.builder()
                        .role(RoleDTO.builder()
                                .name("ADMIN")
                                .build())
                        .assignedDate(LocalDate.now())
                        .build()))
                .address(UserAddressMother.createUserAddressDTO())
                .build();
    }
}
