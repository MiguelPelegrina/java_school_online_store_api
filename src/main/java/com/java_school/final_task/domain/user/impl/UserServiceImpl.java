package com.java_school.final_task.domain.user.impl;

import com.java_school.final_task.domain.user.QUserEntity;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.domain.user.UserService;
import com.java_school.final_task.domain.user.dto.UserDTO;
import com.java_school.final_task.domain.user.dto.UserRequestDTO;
import com.java_school.final_task.exception.user.InsufficientPermissionsException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.utils.StringValues;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

// TODO Too many comments

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link UserRestControllerImpl}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link UserEntity} as
 * {@link UserDTO} to the {@link UserRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CLIENT"})
@Service
public class UserServiceImpl
        extends AbstractServiceImpl<UserRepository, UserEntity, UserDTO, Integer>
        implements UserService {
    private final PasswordEncoder passwordEncoder;

    /**
     * All arguments constructor.
     *
     * @param repository      {@link UserRepository} of the {@link UserEntity} entity.
     * @param modelMapper     ModelMapper that converts the {@link UserEntity} to {@link UserDTO}
     * @param passwordEncoder
     */
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        super(repository, modelMapper);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a UserEntity instance based on authorization and permission checks.
     * This method first retrieves the user's authorization token from the request's headers.
     * It then decodes the token to obtain the user's ID. The method checks if the user exists in the
     * database and is active. If the user is both present and active, it performs the following checks:
     * - If the user is attempting to update themselves, the method allows the update.
     * - If the user is not updating themselves, it checks if the active user has permission to update
     * the target UserEntity instance based on their role:
     * - Admins can update employees and clients but not other admins.
     * - Employees can update clients.
     * If any of these checks fail, an InsufficientPermissions exception is thrown. If the user is not
     * present in the database or is not active, an InsufficientPermissions exception is also thrown.
     *
     * @param toSaveOrUpdateUser The UserEntity instance to be saved.
     * @return The saved UserDTO instance if the operation is allowed.
     * @throws InsufficientPermissionsException If the user does not have sufficient permissions to perform the update.
     */
    @Override
    public UserDTO saveInstance(UserEntity toSaveOrUpdateUser) {
        // Get the token
        int userId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        UserEntity activeExistingUser = repository.findById(userId).orElseThrow(() -> new UserDoesNotExistException(
                toSaveOrUpdateUser.getEmail()
        ));

        // Get the roles and the password of the user that is being updated, if they exist.
        // This is necessary because the password will not always be sent from the frontend (e.g. the current user
        // updates themselves, a user is being activated/deactivated) and I can't create a JSON with circular reference
        Optional<UserEntity> existingUser = repository.findById(toSaveOrUpdateUser.getId());
        existingUser.ifPresent(user -> {
            // Check if the user has set a password.
            // If they set a password, the old one will be overridden.
            // If they didn't set a password any other property is being changed.
            if (user.getPassword().isEmpty()) {
                toSaveOrUpdateUser.setPassword(user.getPassword());
            } else {
                toSaveOrUpdateUser.setPassword(passwordEncoder.encode(toSaveOrUpdateUser.getPassword()));
            }
            toSaveOrUpdateUser.setRoles(user.getRoles());
        });

        // Check if the active user exists and is active
        if (activeExistingUser.isActive()) {
            // Check if the user is trying to update themselves
            if (activeExistingUser.getId() == toSaveOrUpdateUser.getId()) {
                return super.saveInstance(toSaveOrUpdateUser);
            } else {
                // Check if the active user is allowed to update others:
                // - Admin can update employees and clients, but not other admins
                // - Employee can update clients
                if (activeExistingUser.hasMoreRightThen(toSaveOrUpdateUser)) {
                    return super.saveInstance(toSaveOrUpdateUser);
                    // Admins can not update other admins
                    // Employees can not update other employees
                    // Clients can not update others
                } else {
                    throw new InsufficientPermissionsException();
                }
            }
        } else {
            throw new InsufficientPermissionsException();
        }
    }

    @Override
    public Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    public Integer getEntityId(UserEntity instance) {
        return instance.getId();
    }

    @Override
    public UserEntity getCurrentUser() {
        int currentUserId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        return repository.findById(currentUserId).orElseThrow(() ->
                new UserDoesNotExistException(String.format(StringValues.USER_DOES_NOT_EXIST, currentUserId))
        );

    }

    @Override
    public Page<UserDTO> getAllInstances(UserRequestDTO userRequestDTO) {
        // Variables
        final QUserEntity qUser = QUserEntity.userEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();
        int currentUserId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        UserEntity currentUser = repository.findById(currentUserId).orElseThrow(() ->
                new UserDoesNotExistException(String.format(StringValues.USER_DOES_NOT_EXIST, currentUserId))
        );

        // Check if the current user is active
        if (currentUser.isActive() && !currentUser.isClient()) {
            userRequestDTO.getActive().ifPresent(aBoolean -> queryBuilder.and(qUser.active.eq(aBoolean)));

            if (!userRequestDTO.getName().isEmpty()) {
                queryBuilder.and(qUser.name.containsIgnoreCase(userRequestDTO.getName())
                        .or(qUser.surname.containsIgnoreCase(userRequestDTO.getName()))
                        .or(qUser.email.containsIgnoreCase(userRequestDTO.getName())));
            }

            queryBuilder.and(qUser.roles.any().role.name.eq("CLIENT"));

            // Generate the page request
            PageRequest pageRequest = PageRequest.of(
                    userRequestDTO.getPage(),
                    userRequestDTO.getSize(),
                    Sort.Direction.valueOf(userRequestDTO.getSortType()),
                    userRequestDTO.getSortProperty());

            // Find the data in the repository
            Page<UserEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

            // Convert the page to a DTO page
            return pageEntities.map(order -> modelMapper.map(order, this.getDTOClass()));
        } else {
            throw new InsufficientPermissionsException();
        }
    }
}
