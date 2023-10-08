package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookDTO;
import com.javaSchool.FinalTask3.entities.Book;
import com.javaSchool.FinalTask3.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.BookController}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link Book} as {@link BookDTO} to the {@link com.javaSchool.FinalTask3.controller.BookController}.
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository repository;

    private final ModelMapper modelMapper;

    /**
     * Handles the GET request. Obtains all {@link Book}s from the database and returns them as a collection of
     * {@link BookDTO}s.
     * @return Returns a List of all {@link BookDTO}s.
     */
    public List<BookDTO> getAllBooks(){
        return repository.findAll()
                .stream()
                .map(book ->
                        modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Handles the GET request with a specified ID. Obtains the {@link Book} with the specified ID from the database
     * and returns it as a {@link BookDTO}.
     * @param id ID of the {@link Book} that will be searched.
     * @return Returns the searched {@link BookDTO}. If no {@link Book} is found, it returns null.
     */
    public BookDTO getBookById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), BookDTO.class);
    }

    /**
     * Handles the POST request. Saves a {@link Book} into the database.
     * @param book {@link Book} that will be saved into the database.
     * @return Returns the {@link BookDTO}. If the {@link Book} could not be saved, it returns null.
     */
    @Transactional
    public BookDTO saveBook(Book book){
        return modelMapper.map(repository.save(book), BookDTO.class);
    }

    /**
     * Handles the PUT request. Updates an existing {@link Book} in the database.
     * @param id ID of the {@link Book} that will be updated.
     * @param book {@link Book} that that will be updated in the database.
     * @return Returns the updated {@link BookDTO}. If the {@link Book} could not be updated, it returns null.
     */
    @Transactional
    public BookDTO updateBook(int id, Book book){
        return repository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setGenre(book.getGenre());
                    existingBook.setParameters(book.getParameters());
                    existingBook.setStock(book.getStock());
                    existingBook.setActive(book.isActive());
                    existingBook.setImage(book.getImage());
                    return modelMapper.map(repository.save(existingBook), BookDTO.class);
                })
                .orElse(null);
    }

    /**
     * Handles the DELETE request. Deletes specified {@link Book} from the database.
     * @param id ID of the {@link Book} that will be deleted.
     */
    @Transactional
    public void deleteBook(int id){
        repository.deleteById(id);
    }
}
