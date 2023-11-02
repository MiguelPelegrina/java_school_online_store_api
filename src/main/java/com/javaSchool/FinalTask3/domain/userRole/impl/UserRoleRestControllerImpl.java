package com.javaSchool.FinalTask3.domain.userRole.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
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
