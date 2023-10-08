package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.BookParametersFormatDTO;
import com.javaSchool.FinalTask3.entities.BookParametersFormat;
import com.javaSchool.FinalTask3.services.BookParametersFormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RequiredArgsConstructor
@RestController
public class BookParametersFormatController {
    private final BookParametersFormatService service;

    @GetMapping
    public ResponseEntity<List<BookParametersFormatDTO>> getAllBookParametersFormat(){
        return new ResponseEntity<>(service.getAllBookParametersFormat(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookParametersFormatDTO> getBookParametersFormatById(@PathVariable String name){
        return new ResponseEntity<>(service.getBookParametersFormatById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookParametersFormatDTO> saveBookParametersFormat(@RequestBody BookParametersFormat bookParametersFormat){
        BookParametersFormatDTO savedBookParametersFormat = service.saveBookParametersFormat(bookParametersFormat);

        if (savedBookParametersFormat == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedBookParametersFormat, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{name}")
    public void deleteBookParametersFormat(@PathVariable String name){
        service.deleteBookParametersFormat(name);
    }
}
