package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.PostalCodeDTO;
import com.javaSchool.FinalTask3.entities.PostalCode;
import com.javaSchool.FinalTask3.services.PostalCodeService;
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


@RequestMapping(path = "postalcodes")
@RequiredArgsConstructor
@RestController
public class PostalCodeController {
    private final PostalCodeService service;

    @GetMapping
    public ResponseEntity<List<PostalCodeDTO>> getAllPostalCodes(){
        return new ResponseEntity<>(service.getAllPostalCodes(), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<PostalCodeDTO> getPostalCodeById(@PathVariable String code){
        return new ResponseEntity<>(service.getPostalCodeById(code), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostalCodeDTO> savePostalCodeById(@RequestBody PostalCode postalCode){
        PostalCodeDTO savedPostalCode = service.savePostalCode(postalCode);

        if(savedPostalCode == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedPostalCode, HttpStatus.OK);
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<PostalCodeDTO> updatePostalCode(@PathVariable String code, @RequestBody PostalCode postalCode){
        return new ResponseEntity<>(service.updatePostalCode(code, postalCode), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public void deletePostalCode(@PathVariable String code){
        service.deletePostalCode(code);
    }
}
