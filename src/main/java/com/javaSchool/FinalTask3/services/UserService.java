package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.UserDTO;
import com.javaSchool.FinalTask3.entities.User;
import com.javaSchool.FinalTask3.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.UserController}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link User} as
 * {@link UserDTO} to the {@link com.javaSchool.FinalTask3.controller.UserController}.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseServiceWithUpdate<User, UserDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link UserRepository} of the {@link User} entity.
     * @param modelMapper ModelMapper that converts the {@link User} to {@link UserDTO}
     */
    public UserService(UserRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link User} entity.
     * @return Returns the {@link UserDTO} class.
     */
    @Override
    protected Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    /**
     * Updates the values of an existing {@link User} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(User existingInstance, User newInstance) {
        existingInstance.setName(newInstance.getName());
        existingInstance.setSurname(newInstance.getSurname());
        existingInstance.setDateOfBirth(newInstance.getDateOfBirth());
        existingInstance.setEmail(newInstance.getEmail());
        existingInstance.setPassword(newInstance.getPassword());
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setPhoneNumber(newInstance.getPhoneNumber());
        existingInstance.setAddress(newInstance.getAddress());
        existingInstance.setRoles(newInstance.getRoles());
    }
}
