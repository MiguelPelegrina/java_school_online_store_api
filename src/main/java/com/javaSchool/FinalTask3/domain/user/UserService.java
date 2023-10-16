package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link UserRestController}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link UserEntity} as
 * {@link UserDTO} to the {@link UserRestController}.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends AbstractService<UserEntity, UserDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserRepository} of the {@link UserEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserEntity} to {@link UserDTO}
     */
    public UserService(UserRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link UserEntity} entity.
     * @return Returns the {@link UserDTO} class.
     */
    @Override
    protected Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    protected Integer getEntityId(UserEntity instance) {
        return instance.getId();
    }
}
