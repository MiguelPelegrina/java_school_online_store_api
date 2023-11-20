package com.java_school.final_task.domain.user.impl;

import com.javaSchool.finalTask.domain.user.*;
import com.java_school.final_task.domain.user.*;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserEntity} entity. Handles the REST methods. Uses
 * {@link UserDTO} as returning object.
 */
@RequestMapping(path = "users")
@RestController
public class UserRestControllerImpl
        extends AbstractRestControllerImpl<UserServiceImpl, UserRepository, UserEntity, UserDTO, Integer>
        implements UserRestController {
    /**
     * All arguments constructor.
     * @param service         {@link UserServiceImpl} of the {@link UserEntity} entity.
     */
    public UserRestControllerImpl(UserServiceImpl service) {
        super(service);
    }


    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<UserDTO>> getAllInstances(UserRequest userRequest) {
        return ResponseEntity.ok(this.service.getAllInstances(userRequest));
    }
}
