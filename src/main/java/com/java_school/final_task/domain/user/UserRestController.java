package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.dto.UserDTO;
import com.java_school.final_task.domain.user.dto.UserRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * RestController interface of the {@link UserEntity} entity. Handles the REST methods. Uses {@link UserDTO} as returning object.
 */
public interface UserRestController {
    /**
     * Retrieves a page of {@link UserDTO}s from the database based on {@link UserRequestDTO}
     *
     * @param userRequestDTO {@link UserRequestDTO} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link UserDTO}s based on the specified criteria.
     */
    ResponseEntity<Page<UserDTO>> getAllInstances(UserRequestDTO userRequestDTO);
}
