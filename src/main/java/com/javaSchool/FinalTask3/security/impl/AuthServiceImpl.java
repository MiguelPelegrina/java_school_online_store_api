package com.javaSchool.FinalTask3.security.impl;

import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeRepository;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.exception.EmailAlreadyUsedException;
import com.javaSchool.FinalTask3.security.AuthService;
import com.javaSchool.FinalTask3.security.dto.RegisterRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

/**
 * The {@code AuthServiceImpl} class is responsible for user registration and related authentication operations.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    // Fields
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PostalCodeRepository postalCodeRepository;

    /**
     * Registers a new user based on the provided registration data.
     *
     * @param registerRequestBodyDTO The registration data, including user details.
     * @return The registered user entity.
     * @throws EmailAlreadyUsedException if user's email is already being used.
     */
    @Override
    public UserEntity register(RegisterRequestBodyDTO registerRequestBodyDTO) throws EmailAlreadyUsedException {
        // Check if a user with the same email already exists in the repository.
        final Optional<UserEntity> userInRepository = userRepository.findUserByEmail(registerRequestBodyDTO.getEmail());
        if (userInRepository.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        // Build a user
        UserEntity newUser = UserEntity.builder()
                .dateOfBirth(registerRequestBodyDTO.getDateOfBirth())
                .email(registerRequestBodyDTO.getEmail())
                .isActive(true)
                .name(registerRequestBodyDTO.getName())
                .password(passwordEncoder.encode(registerRequestBodyDTO.getPassword()))
                .surname(registerRequestBodyDTO.getSurname())
                .phone(registerRequestBodyDTO.getPhone())
                .roles(new HashSet<>())
                .build();

        // Generate a role. The registered user is assigned the default of CLIENT. In case an employee registers
        // themselves, the ADMIN can assign a new role to them (EMPLOYEE) to them afterward. Might create a page for it
        // in frontend, but right now it's not part of the requirements
        UserRoleEntity newUserRole = UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .user(newUser)
                .role(new RoleEntity("CLIENT"))
                .build();

        newUser.getRoles().add(newUserRole);

        // TODO Not sure if right, other error?
        // Get the postal code from the repository
        PostalCodeEntity postalCode = postalCodeRepository.findById(registerRequestBodyDTO.getAddress().getPostalCode()).orElseThrow();

        // Generate a user address
        UserAddressEntity newUserAddress = UserAddressEntity.builder()
                .postalCode(postalCode)
                .number(registerRequestBodyDTO.getAddress().getNumber())
                .street(registerRequestBodyDTO.getAddress().getStreet())
                .isActive(true)
                .build();

        newUser.setAddress(newUserAddress);

        return userRepository.save(newUser);
    }
}
