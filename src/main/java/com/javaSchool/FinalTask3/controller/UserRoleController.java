package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.UserRoleDTO;
import com.javaSchool.FinalTask3.entities.UserRole;
import com.javaSchool.FinalTask3.services.UserRoleService;
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

@RequestMapping("userroles")
@RequiredArgsConstructor
@RestController
public class UserRoleController {
    private final UserRoleService service;

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles(){
        return new ResponseEntity<>(service.getAllUserRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable int id){
        return new ResponseEntity<>(service.getUserRoleById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> saveUserRole(@RequestBody UserRole userRole){
        UserRoleDTO savedUserRole = service.saveUserRole(userRole);

        if (savedUserRole == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedUserRole, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRole(@PathVariable int id, @RequestBody UserRole userRole){
        return new ResponseEntity<>(service.updateUserRole(id, userRole), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable int id){
        service.deleteUserRole(id);
    }
}
