package com.javaSchool.FinalTask3.domain.userAddress;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserAddressEntity} entity. Handles the REST methods. Uses
 * {@link UserAddressDTO} as returning object.
 */
@RequestMapping(path = "useraddresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserAddressRestController extends AbstractRestControllerWithUpdate<UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link UserAddressService} of the {@link UserAddressEntity} entity.
     */
    public UserAddressRestController(AbstractServiceWithUpdate<UserAddressEntity, UserAddressDTO, Integer> service) {
        super(service);
    }
}
