package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.UserRoleDTO;
import com.javaSchool.FinalTask3.entities.UserRole;
import com.javaSchool.FinalTask3.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository repository;

    private final ModelMapper modelMapper;

    public List<UserRoleDTO> getAllUserRoles(){
        return repository.findAll()
                .stream()
                .map((userRole) ->
                        modelMapper.map(userRole, UserRoleDTO.class))
                .collect(Collectors.toList());
    }

    public UserRoleDTO getUserRoleById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), UserRoleDTO.class);
    }

    @Transactional
    public UserRoleDTO saveUserRole(UserRole userRole){
        return modelMapper.map(repository.save(userRole), UserRoleDTO.class);
    }

    @Transactional
    public UserRoleDTO updateUserRole(int id, UserRole userRole){
        return repository.findById(id).map(existingUserRole -> {
                    existingUserRole.setUser(userRole.getUser());
                    existingUserRole.setRole(userRole.getRole());
                    existingUserRole.setAssignedDate(userRole.getAssignedDate());
                    return modelMapper.map(repository.save(existingUserRole), UserRoleDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteUserRole(int id){
        repository.deleteById(id);
    }
}
