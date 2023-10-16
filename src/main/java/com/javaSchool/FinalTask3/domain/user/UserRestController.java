package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserEntity} entity. Handles the REST methods. Uses
 * {@link UserDTO} as returning object.
 */
@RequestMapping("/users")
@RestController
public class UserRestController extends AbstractRestController<UserEntity, UserDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserService} of the {@link UserEntity} entity.
     */
    public UserRestController(AbstractService<UserEntity, UserDTO, Integer> service) {
        super(service);
    }
}
