package com.javaSchool.FinalTask3.domain.user.userAddress;

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
@RequestMapping(path = "user_addresses")
@RestController
public class UserAddressRestControllerImpl extends AbstractRestControllerImpl<UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserAddressServiceImpl} of the {@link UserAddressEntity} entity.
     */
    public UserAddressRestControllerImpl(AbstractServiceImpl<UserAddressEntity, UserAddressDTO, Integer> service) {
        super(service);
    }
}
