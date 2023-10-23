package com.javaSchool.FinalTask3.domain.bookGenre;

import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookGenreEntity} entity. Handles the REST methods. Uses {@link BookGenreDTO} as
 * returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "book_genres")
@RestController
public class BookGenreRestControllerImpl extends AbstractRestControllerImpl<BookGenreEntity, BookGenreDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookGenreServiceImpl} of the {@link BookGenreEntity} entity.
     */
    public BookGenreRestControllerImpl(AbstractServiceImpl<BookGenreEntity, BookGenreDTO, String> service){
        super(service);
    }
}
