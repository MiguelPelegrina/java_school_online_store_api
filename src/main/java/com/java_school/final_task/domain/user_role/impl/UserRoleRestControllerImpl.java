package com.java_school.final_task.domain.user_role.impl;

import com.java_school.final_task.domain.user.user_address.UserAddressDTO;
import com.java_school.final_task.domain.user.user_address.UserAddressEntity;
import com.java_school.final_task.domain.user_role.dto.UserRoleDTO;
import com.java_school.final_task.domain.user_role.UserRoleEntity;
import com.java_school.final_task.domain.user_role.UserRoleRepository;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserAddressEntity} entity. Handles the REST methods. Uses
 * {@link UserAddressDTO} as returning object.
 */
@RequestMapping(path = "user_roles")
@RestController
public class UserRoleRestControllerImpl
        extends AbstractRestControllerImpl<UserRoleServiceImpl, UserRoleRepository, UserRoleEntity, UserRoleDTO, Integer> {
    /**
     * All arguments constructor.
     *
     * @param service {@link UserRoleServiceImpl} of the {@link UserRoleEntity} entity.
     */
    public UserRoleRestControllerImpl(UserRoleServiceImpl service) {
        super(service);
    }
}
