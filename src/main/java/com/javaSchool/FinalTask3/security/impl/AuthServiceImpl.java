package com.javaSchool.FinalTask3.security.impl;

import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
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
    private final UserRepository repository;

    /**
     * Registers a new user based on the provided registration data.
     * @param registerRequestBodyDTO The registration data, including user details.
     * @return The registered user entity.
     * @throws EmailAlreadyUsedException if user's email is already being used.
     */
    @Override
    public UserEntity register(RegisterRequestBodyDTO registerRequestBodyDTO) throws EmailAlreadyUsedException {
         // Check if a user with the same email already exists in the repository.
         final Optional<UserEntity> userInRepository = repository.findUserByEmail(registerRequestBodyDTO.getEmail());
         if(userInRepository.isPresent()){
             throw new EmailAlreadyUsedException();
         }

         UserEntity newUser = UserEntity.builder()
                .dateOfBirth(registerRequestBodyDTO.getDateOfBirth())
                .email(registerRequestBodyDTO.getEmail())
                .isActive(true)
                .name(registerRequestBodyDTO.getName())
                .password(passwordEncoder.encode(registerRequestBodyDTO.getPassword()))
                .surname(registerRequestBodyDTO.getSurname())
                .phoneNumber(registerRequestBodyDTO.getPhone())
                .roles(new HashSet<>())
                .build();

         UserRoleEntity newUserRole = UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .user(newUser)
                .role(new RoleEntity("CLIENT"))
                .build();

         newUser.getRoles().add(newUserRole);

         return repository.save(newUser);
    }
}
