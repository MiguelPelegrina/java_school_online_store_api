package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.UserDTO;
import com.javaSchool.FinalTask3.entities.User;
import com.javaSchool.FinalTask3.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;

    private final ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        return repository.findAll()
                .stream()
                .map((user) ->
                        modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), UserDTO.class);
    }

    @Transactional
    public UserDTO saveUser(User user){
        return modelMapper.map(repository.save(user), UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(int id, User user){
        return repository.findById(id).map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setSurname(user.getSurname());
                    existingUser.setDateOfBirth(user.getDateOfBirth());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setActive(user.isActive());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    existingUser.setAddress(user.getAddress());
                    existingUser.setRoles(user.getRoles());
                    return modelMapper.map(repository.save(existingUser),UserDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteUser(int id){
        repository.deleteById(id);
    }
}
