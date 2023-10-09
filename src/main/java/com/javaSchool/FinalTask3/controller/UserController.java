package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.UserDTO;
import com.javaSchool.FinalTask3.entities.User;
import com.javaSchool.FinalTask3.services.UserService;
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

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user){
        UserDTO savedUser = service.saveInstance(user);

        if (savedUser == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user){
        return new ResponseEntity<>(service.updateInstance(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteInstance(id);
    }
}
