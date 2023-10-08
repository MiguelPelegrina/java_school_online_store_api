package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.BookParameterDTO;
import com.javaSchool.FinalTask3.entities.BookParameter;
import com.javaSchool.FinalTask3.services.BookParameterService;
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

@RequestMapping("bookparameters")
@RequiredArgsConstructor
@RestController
public class BookParameterController {
    private final BookParameterService service;

    @GetMapping
    public ResponseEntity<List<BookParameterDTO>> getBookParameter(){
        return new ResponseEntity<>(service.getAllBookParameters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookParameterDTO> getBookParameterById(@PathVariable int id){
        return new ResponseEntity<>(service.getBookParametersById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookParameterDTO> saveBookParameter(@RequestBody BookParameter bookParameter){
        BookParameterDTO savedBookParameter = service.saveBookParameter(bookParameter);

        if(savedBookParameter == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedBookParameter, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookParameterDTO> updateBookParameter(@PathVariable int id, @RequestBody BookParameter bookParameter){
        return new ResponseEntity<>(service.updateBookParameter(id, bookParameter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBookParameter(@PathVariable int id){
        service.deleteBookParameter(id);
    }
}
