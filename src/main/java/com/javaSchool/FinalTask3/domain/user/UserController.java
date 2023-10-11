package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserEntity} entity. Handles the REST methods. Uses
 * {@link UserDTO} as returning object.
 */
@RequestMapping("/users")
@RestController
public class UserController extends AbstractRestControllerWithUpdate<UserEntity, UserDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserService} of the {@link UserEntity} entity.
     */
    public UserController(AbstractServiceWithUpdate<UserEntity, UserDTO, Integer> service) {
        super(service);
    }
}
