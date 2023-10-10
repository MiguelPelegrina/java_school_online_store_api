package com.javaSchool.FinalTask3.domain.bookGenre;

import com.javaSchool.FinalTask3.utils.AbstractService;
import com.javaSchool.FinalTask3.utils.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("bookgenres")
@RestController
public class BookGenreController extends AbstractController<BookGenreEntity, BookGenreDTO, String> {
    public BookGenreController(AbstractService<BookGenreEntity, BookGenreDTO, String> service){
        super(service);
    }
}
