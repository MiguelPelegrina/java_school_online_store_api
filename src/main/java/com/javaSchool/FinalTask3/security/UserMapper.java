package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeRepository;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.security.dto.RegisterRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Provides methods for mapping {@link RegisterRequestBodyDTO} to {@link UserEntity}, including default values and
 * additional logic for user registration.
 */
@Component
@RequiredArgsConstructor
public class UserMapper {
    // Fields
    private final PasswordEncoder passwordEncoder;
    private final PostalCodeRepository postalCodeRepository;

    /**
     * Maps a {@link RegisterRequestBodyDTO} to a new {@link UserEntity}, applying default values and additional logic
     * for registration.
     * @param registerRequestBodyDTO The DTO containing user registration information.
     * @return New {@link UserEntity} with mapped values from the provided {@link RegisterRequestBodyDTO}.
     * @throws RuntimeException If there are issues retrieving postal code information from the repository.
     */
    @Transactional(readOnly = true)
    public UserEntity mapToUserEntity(RegisterRequestBodyDTO registerRequestBodyDTO){
        UserEntity newUser = UserEntity.builder()
                .dateOfBirth(registerRequestBodyDTO.getDateOfBirth())
                .email(registerRequestBodyDTO.getEmail())
                .active(true)
                .name(registerRequestBodyDTO.getName())
                .password(passwordEncoder.encode(registerRequestBodyDTO.getPassword()))
                .surname(registerRequestBodyDTO.getSurname())
                .phone(registerRequestBodyDTO.getPhone())
                .roles(new HashSet<>())
                .build();

        // Applies by default to all registered users
        // Activity, true by default to enable user to buy without being enabled by an employee or an admin
        newUser.setActive(true);
        // Encodes the password for security
        newUser.setPassword(passwordEncoder.encode(registerRequestBodyDTO.getPassword()));
        // In case an employee registers themselves, the ADMIN can assign a new role to them (EMPLOYEE) to them
        // afterward. Might create a page for it in frontend, but right now it's not part of the requirements.
        UserRoleEntity newUserRole = UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .user(newUser)
                .role(new RoleEntity("CLIENT"))
                .build();
        newUser.getRoles().add(newUserRole);

        // As only the postal code is sent in the request, the rest of the data is retrieved from the repository
        PostalCodeEntity postalCode = postalCodeRepository.findById(registerRequestBodyDTO.getAddress().getPostalCode()).orElseThrow();
        // Generate a user address
        UserAddressEntity newUserAddress = UserAddressEntity.builder()
                .postalCode(postalCode)
                .number(registerRequestBodyDTO.getAddress().getNumber())
                .street(registerRequestBodyDTO.getAddress().getStreet())
                .isActive(true)
                .build();

        newUser.setAddress(newUserAddress);

        return newUser;
    }
}
