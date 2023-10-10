package com.javaSchool.FinalTask3.domain.book;

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

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@RequestMapping(path = "books")
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService service;

    /**
     * Submits a GET request to "/books" to obtain all {@link BookEntity}s from the database.
     * @return Returns a ResponseEntity with all the {@link BookEntity}s ({@link BookDTO}s) and the status of the GET request.
     * If successful, the code is 200.
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    /**
     * Submits a GET request to "books/{id}" to get details of a {@link BookEntity} from the database.
     * @param id ID of the {@link BookEntity} that is searched.
     * @return Returns a ResponseEntity with the {@link BookEntity} ({@link BookDTO}) and the status of the GET request.
     * If successful, the code is 200.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    /**
     * Submits a POST request to "/books" with a RequestBody to create a {@link BookEntity} in the database.
     * @param book {@link BookEntity} to create.
     * @return Returns a ResponseEntity with the {@link BookEntity} ({@link BookDTO}) and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookEntity book){
        BookDTO savedBook = service.saveInstance(book);

        if(savedBook == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        }
    }

    /**
     * Submits a PUT request to "books/{id}" to update an existing {@link BookEntity} in the database.
     * @param id ID of the {@link BookEntity} that will be updated.
     * @param book {@link BookEntity} with the updated attributes.
     * @return Returns a ResponseEntity with the {@link BookEntity} ({@link BookDTO}) and the status of the PUT request.
     * If successful, the code is 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookEntity book){
        return new ResponseEntity<>(service.updateInstance(id, book), HttpStatus.OK);
    }

    /**
     * Submits a DELETE request to "books/{id}" to delete an existing {@link BookEntity} from the database.
     * @param id ID of the {@link BookEntity} that will be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        service.deleteInstance(id);
    }
}
