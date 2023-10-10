package com.javaSchool.FinalTask3.domain.bookParameter;

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
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookParameterDTO> getBookParameterById(@PathVariable int id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookParameterDTO> saveBookParameter(@RequestBody BookParameterEntity bookParameter){
        BookParameterDTO savedBookParameter = service.saveInstance(bookParameter);

        if(savedBookParameter == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedBookParameter, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookParameterDTO> updateBookParameter(@PathVariable int id, @RequestBody BookParameterEntity bookParameter){
        return new ResponseEntity<>(service.updateInstance(id, bookParameter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBookParameter(@PathVariable int id){
        service.deleteInstance(id);
    }
}
