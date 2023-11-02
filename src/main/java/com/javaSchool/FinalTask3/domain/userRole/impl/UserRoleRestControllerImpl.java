package com.javaSchool.FinalTask3.domain.userRole.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserAddressEntity} entity. Handles the REST methods. Uses
 * {@link UserAddressDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "userroles")
@RestController
public class UserRoleRestControllerImpl extends AbstractRestControllerImpl<UserRoleEntity, UserRoleDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserRoleServiceImpl} of the {@link UserRoleEntity} entity.
     */
    public UserRoleRestControllerImpl(AbstractServiceImpl<UserRoleEntity, UserRoleDTO, Integer> service) {
        super(service);
    }
}
