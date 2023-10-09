package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookDTO;
import com.javaSchool.FinalTask3.entities.Book;
import com.javaSchool.FinalTask3.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.BookController}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link Book} as {@link BookDTO} to the {@link com.javaSchool.FinalTask3.controller.BookController}.
 */
@Service
@Transactional(readOnly = true)
public class BookService extends BaseServiceWithUpdate<Book, BookDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link BookRepository} of the {@link Book} entity.
     * @param modelMapper ModelMapper that converts the {@link Book} to {@link BookDTO}
     */
    public BookService(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link Book} entity.
     * @return Returns the {@link BookDTO} class.
     */
    @Override
    protected Class<BookDTO> getDTOClass() {
        return BookDTO.class;
    }

    /**
     * Updates the values of an existing {@link Book} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(Book existingInstance, Book newInstance) {
        existingInstance.setTitle(newInstance.getTitle());
        existingInstance.setPrice(newInstance.getPrice());
        existingInstance.setIsbn(newInstance.getIsbn());
        existingInstance.setGenre(newInstance.getGenre());
        existingInstance.setParameters(newInstance.getParameters());
        existingInstance.setStock(newInstance.getStock());
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setImage(newInstance.getImage());
    }
}
