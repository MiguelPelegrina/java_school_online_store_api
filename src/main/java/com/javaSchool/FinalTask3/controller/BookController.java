package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.BookDTO;
import com.javaSchool.FinalTask3.entities.Book;
import com.javaSchool.FinalTask3.services.BookService;
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

@RequestMapping(path = "books")
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id){
        return new ResponseEntity<>(service.getBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody Book book){
        BookDTO savedBook = service.saveBook(book);

        if(savedBook == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody Book book){
        return new ResponseEntity<>(service.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        service.deleteBook(id);
    }
}
