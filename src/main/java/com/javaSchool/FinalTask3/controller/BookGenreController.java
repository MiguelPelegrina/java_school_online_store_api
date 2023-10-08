package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.services.BookGenreService;
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

@RequestMapping("bookgenres")
@RequiredArgsConstructor
@RestController
public class BookGenreController {
    private final BookGenreService service;

    @GetMapping
    public ResponseEntity<List<BookGenreDTO>> getAllBookGenres(){
        return new ResponseEntity<>(service.getAllBookGenres(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookGenreDTO> getBookGenreById(@PathVariable String name){
        return new ResponseEntity<>(service.getBookGenreById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookGenreDTO> saveBookGenre(@RequestBody BookGenre bookGenre){
        BookGenreDTO savedBookGenre = service.saveBookGenre(bookGenre);

        if (savedBookGenre == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(service.saveBookGenre(bookGenre), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{name}")
    public void deleteBookGenre(@PathVariable String name){
        service.deleteBookGenre(name);
    }
}
