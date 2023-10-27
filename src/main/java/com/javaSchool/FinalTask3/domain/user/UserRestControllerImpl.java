package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserEntity} entity. Handles the REST methods. Uses
 * {@link UserDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserRestControllerImpl extends AbstractRestControllerImpl<UserEntity, UserDTO, Integer> {
    /**
     * All arguments constructor.
     *
     * @param service         {@link UserServiceImpl} of the {@link UserEntity} entity.
     */
    public UserRestControllerImpl(AbstractServiceImpl<UserEntity, UserDTO, Integer> service) {
        super(service);
    }

    // TODO Override method of saveInstance to encode password?
    // TODO How to apply Annotation @Secured to methods that are in the parent class properly?
    // @Secured({"ROLE_ADMIN", "ROLE_USER"})
}
