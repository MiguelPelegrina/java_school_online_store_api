package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.dto.UserDTO;
import com.java_school.final_task.domain.user.dto.UserRequestDTO;
import com.java_school.final_task.domain.user.impl.UserRestControllerImpl;
import org.springframework.data.domain.Page;

/**
 * Service interface responsible for the interaction between the {@link UserRepository} and the
 * {@link UserRestControllerImpl}. Obtains data from the {@link UserRepository} and returns
 * the object(s) of the entity {@link UserEntity} as {@link UserDTO} to the {@link UserRestControllerImpl}.
 */
public interface UserService {
    /**
     * Retrieves a page of {@link UserDTO}s from the database based on {@link UserRequestDTO}.
     *
     * @param userRequestDTO {@link UserRequestDTO} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link UserDTO}}s based on the specified criteria.
     */
    Page<UserDTO> getAllInstances(UserRequestDTO userRequestDTO);
}
