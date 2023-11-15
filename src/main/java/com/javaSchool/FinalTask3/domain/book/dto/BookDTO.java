package com.javaSchool.FinalTask3.domain.book.dto;

import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) of {@link BookEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BookDTO {
    private int id;
    private Boolean active;
    private String title;
    private BigDecimal price;
    private BookGenreDTO genre;
    private String isbn;
    private BookParameterDTO parameters;
    private int stock;
    private boolean isActive;
    private String image;
}
