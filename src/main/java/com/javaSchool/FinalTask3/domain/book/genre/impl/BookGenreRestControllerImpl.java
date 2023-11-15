package com.javaSchool.FinalTask3.domain.book.genre.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookGenreEntity} entity. Handles the REST methods. Uses {@link BookGenreDTO} as
 * returning object.
 */
@RequestMapping(path = "book_genres")
@RestController
public class BookGenreRestControllerImpl
        extends AbstractRestControllerImpl<BookGenreServiceImpl, BookGenreRepository, BookGenreEntity, BookGenreDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookGenreServiceImpl} of the {@link BookGenreEntity} entity.
     */
    public BookGenreRestControllerImpl(BookGenreServiceImpl service){
        super(service);
    }
}
