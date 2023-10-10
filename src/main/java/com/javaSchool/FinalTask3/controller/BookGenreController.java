package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.services.BaseService;
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
@RestController
public class BookGenreController extends BaseController<BookGenre, BookGenreDTO, String>{
    public BookGenreController(BaseService<BookGenre, BookGenreDTO, String> service){
        super(service);
    }
}
