package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.UserAddressDTO;
import com.javaSchool.FinalTask3.entities.UserAddress;
import com.javaSchool.FinalTask3.services.UserAddressService;
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

@RequestMapping("useraddresses")
@RequiredArgsConstructor
@RestController
public class UserAddressController {
    private final UserAddressService service;

    @GetMapping
    public ResponseEntity<List<UserAddressDTO>> getAllUserAddresses(){
        return new ResponseEntity<>(service.getAllUserAddresses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDTO> getUserAddressById(@PathVariable int id){
        return new ResponseEntity<>(service.getUserAddressById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserAddressDTO> saveUserAddress(@RequestBody UserAddress userAddress){
        UserAddressDTO savedUserAddress = service.saveUserAddress(userAddress);

        if (savedUserAddress == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedUserAddress, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddressDTO> updateUserAddress(@PathVariable int id, @RequestBody UserAddress userAddress){
        return new ResponseEntity<>(service.updateUserAddress(id, userAddress), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUserAddress(@PathVariable int id){
        service.deleteUserAddress(id);
    }

}
