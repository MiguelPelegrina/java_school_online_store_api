package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.RoleDTO;
import com.javaSchool.FinalTask3.entities.Role;
import com.javaSchool.FinalTask3.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository repository;

    private final ModelMapper modelMapper;

    public List<RoleDTO> getAllRoles(){
        return repository.findAll()
                .stream()
                .map((role) ->
                        modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleById(String id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), RoleDTO.class);
    }

    @Transactional
    public RoleDTO saveRole(Role role){
        return modelMapper.map(repository.save(role), RoleDTO.class);
    }

    @Transactional
    public RoleDTO updateRole(String id, Role role){
        return repository.findById(id).map(existingRole -> {
                    existingRole.setRoles(role.getRoles());
                    return modelMapper.map(role, RoleDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteRole(String name){
        repository.deleteById(name);
    }
}
