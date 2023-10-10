package com.javaSchool.FinalTask3.domain.userRole;

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
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable int id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> saveUserRole(@RequestBody UserRole userRole){
        UserRoleDTO savedUserRole = service.saveInstance(userRole);

        if (savedUserRole == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedUserRole, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRole(@PathVariable int id, @RequestBody UserRole userRole){
        return new ResponseEntity<>(service.updateInstance(id, userRole), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable int id){
        service.deleteInstance(id);
    }
}
