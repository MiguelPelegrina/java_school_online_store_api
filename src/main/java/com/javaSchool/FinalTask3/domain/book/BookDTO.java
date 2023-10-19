package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.bookParameter.BookParameterDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) of {@link BookEntity}.
 */
@Data
public class BookDTO {
    private int id;
    private String title;
    private BigDecimal price;
    private BookGenreDTO genre;
    private String isbn;
    // TODO Not sure if right
    private BookParameterDTO parameters;
    private int stock;
    private boolean isActive;
    private String image;
}
