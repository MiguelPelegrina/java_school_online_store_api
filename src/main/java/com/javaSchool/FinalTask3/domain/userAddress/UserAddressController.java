package com.javaSchool.FinalTask3.domain.userAddress;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserAddressEntity} entity. Handles the REST methods. Uses
 * {@link UserAddressDTO} as returning object.
 */
@RequestMapping("useraddresses")
@RestController
public class UserAddressController extends AbstractRestControllerWithUpdate<UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserAddressService} of the {@link UserAddressEntity} entity.
     */
    public UserAddressController(AbstractServiceWithUpdate<UserAddressEntity, UserAddressDTO, Integer> service) {
        super(service);
    }
}
