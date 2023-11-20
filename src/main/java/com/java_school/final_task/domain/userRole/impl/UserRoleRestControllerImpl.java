package com.java_school.final_task.domain.userRole.impl;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleDTO;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.UserRoleRepository;
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
     * @param service {@link UserRoleServiceImpl} of the {@link UserRoleEntity} entity.
     */
    public UserRoleRestControllerImpl(UserRoleServiceImpl service) {
        super(service);
    }
}
