package com.javaSchool.FinalTask3.domain.user.impl;

import com.javaSchool.FinalTask3.domain.user.UserDTO;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import com.javaSchool.FinalTask3.exception.InsufficientPermissions;
import com.javaSchool.FinalTask3.security.JwtUtil;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link UserRestControllerImpl}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link UserEntity} as
 * {@link UserDTO} to the {@link UserRestControllerImpl}.
 */
@Secured("ROLE_ADMIN")
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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        System.out.println(request.getHeader("Authorization"));

        // Parse the token to get the id
        Claims claims = new JwtUtil().decodeToken(request.getHeader("Authorization").substring(7));
        int userId = claims.get("id", Integer.class);

        // Get the user from the database
        Optional<UserEntity> activeUser = repository.findById(userId);

        // Check if the active user exists and is active
        if(activeUser.isPresent() && activeUser.get().isActive()){
            // Check if the user is trying to update themselves
            if(activeUser.get().getId() == instance.getId()){
                return super.saveInstance(instance);
            } else {
                // Check if the active user is allowed to update others:
                // - Admin can update employees and clients, but not other admins
                // - Employee can update clients
                if(UserEntity.isAllowedToUpdate(activeUser.get(), instance)){
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
