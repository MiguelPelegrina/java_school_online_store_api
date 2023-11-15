package com.javaSchool.FinalTask3.domain.book.dto;

import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NumberedBookDTO extends BookDTO {
    private int totalAmount;

    public NumberedBookDTO(BookEntity book, int totalAmount){
        super();
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setPrice(book.getPrice());
        this.setGenre(new BookGenreDTO(book.getGenre().getName()));
        this.setIsbn(book.getIsbn());
        this.setParameters(new BookParameterDTO(book.getParameters()));
        this.setStock(book.getStock());
        this.setActive(book.isActive());
        this.setImage(book.getImage());
        this.totalAmount = totalAmount;
    }
}
