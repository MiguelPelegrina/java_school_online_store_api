package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.RoleDTO;
import com.javaSchool.FinalTask3.entities.Role;
import com.javaSchool.FinalTask3.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("roles")
@RequiredArgsConstructor
@RestController
public class RoleController {
    private final RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable String name){
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> saveRole(@RequestBody Role role){
        RoleDTO savedRole = service.saveInstance(role);

        if(savedRole == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable String name, @RequestBody Role role){
        return new ResponseEntity<>(service.updateInstance(name, role), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteRole(@PathVariable String name){
        service.deleteInstance(name);
    }
}
