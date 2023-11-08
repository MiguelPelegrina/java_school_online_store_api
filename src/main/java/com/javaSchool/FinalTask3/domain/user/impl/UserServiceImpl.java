package com.javaSchool.FinalTask3.domain.user.impl;

import com.javaSchool.FinalTask3.domain.user.UserDTO;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import com.javaSchool.FinalTask3.exception.InsufficientPermissions;
import com.javaSchool.FinalTask3.exception.UserDoesNotExist;
import com.javaSchool.FinalTask3.security.JwtUtil;
import com.javaSchool.FinalTask3.utils.StringValues;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link UserRestControllerImpl}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link UserEntity} as
 * {@link UserDTO} to the {@link UserRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CLIENT"})
@Service
@Transactional(readOnly = true)
public class UserServiceImpl
        extends AbstractServiceImpl<UserRepository, UserEntity, UserDTO, Integer> {

    /**
     * All arguments constructor.
     * @param repository {@link UserRepository} of the {@link UserEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserEntity} to {@link UserDTO}
     */
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Saves a UserEntity instance based on authorization and permission checks.
     * This method first retrieves the user's authorization token from the request's headers.
     * It then decodes the token to obtain the user's ID. The method checks if the user exists in the
     * database and is active. If the user is both present and active, it performs the following checks:
     * - If the user is attempting to update themselves, the method allows the update.
     * - If the user is not updating themselves, it checks if the active user has permission to update
     *   the target UserEntity instance based on their role:
     *   - Admins can update employees and clients but not other admins.
     *   - Employees can update clients.
     * If any of these checks fail, an InsufficientPermissions exception is thrown. If the user is not
     * present in the database or is not active, an InsufficientPermissions exception is also thrown.
     * @param instance The UserEntity instance to be saved.
     * @return The saved UserDTO instance if the operation is allowed.
     * @throws InsufficientPermissions If the user does not have sufficient permissions to perform the update.
     */
    @Override
    public UserDTO saveInstance(UserEntity instance) {
        // Get the token
        int userId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        UserEntity existingUser = repository.findById(userId).orElseThrow(() -> new UserDoesNotExist(
                String.format(StringValues.INSTANCE_NOT_FOUND, userId)
        ));

        // Check if the active user exists and is active
        if(existingUser.isActive()){
            // Check if the user is trying to update themselves
            if(existingUser.getId() == instance.getId()){
                return super.saveInstance(instance);
            } else {
                // Check if the active user is allowed to update others:
                // - Admin can update employees and clients, but not other admins
                // - Employee can update clients
                if(existingUser.hasMoreRightThen(instance)){
                    return super.saveInstance(instance);
                    // Admins can not update other admins
                    // Employees can not update other employees
                    // Clients can not update others
                } else {
                    throw new InsufficientPermissions();
                }
            }
        } else {
            throw new InsufficientPermissions();
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
}
