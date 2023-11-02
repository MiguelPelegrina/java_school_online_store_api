package com.javaSchool.FinalTask3.domain.role.impl;

import com.javaSchool.FinalTask3.domain.role.RoleDTO;
import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link RoleEntity} entity. Handles the REST methods. Uses
 * {@link RoleDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "roles")
@RestController
public class RoleRestControllerImpl extends AbstractRestControllerImpl<RoleEntity, RoleDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link RoleServiceImpl} of the {@link RoleEntity} entity.
     */
    public RoleRestControllerImpl(AbstractServiceImpl<RoleEntity, RoleDTO, String> service) {
        super(service);
    }
}
