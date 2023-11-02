package com.javaSchool.FinalTask3.domain.user.userAddress.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserAddressEntity} entity. Handles the REST methods. Uses
 * {@link UserAddressDTO} as returning object.
 */
@RequestMapping(path = "user_addresses")
@RestController
public class UserAddressRestControllerImpl
        extends AbstractRestControllerImpl<UserAddressServiceImpl, UserAddressRepository, UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserAddressServiceImpl} of the {@link UserAddressEntity} entity.
     */
    public UserAddressRestControllerImpl(UserAddressServiceImpl service) {
        super(service);
    }
}
