package com.javaSchool.FinalTask3.domain.bookGenre;

import com.javaSchool.FinalTask3.utils.AbstractService;
import com.javaSchool.FinalTask3.utils.AbstractRestController;
import org.springframework.http.MediaType;
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
public class BookGenreRestController extends AbstractRestController<BookGenreEntity, BookGenreDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookGenreService} of the {@link BookGenreEntity} entity.
     */
    public BookGenreRestController(AbstractService<BookGenreEntity, BookGenreDTO, String> service){
        super(service);
    }
}
