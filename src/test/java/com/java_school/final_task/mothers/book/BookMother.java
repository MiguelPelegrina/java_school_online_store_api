package com.java_school.final_task.mothers.book;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.mothers.book.parameter.BookParameterMother;

import java.math.BigDecimal;

// TODO Randomize values
public class BookMother {
    public static BookEntity createBook(){
        return BookEntity.builder()
                .id(1)
                .title("Title")
                .active(true)
                .genre(new BookGenreEntity("Genre"))
                .image("Image")
                .isbn("ISBN")
                .price(new BigDecimal("1.23"))
                .stock(10)
                .parameters(BookParameterMother.createBookParameter())
                .build();
    }

    public static BookDTO createBookDTO(){
        return BookDTO.builder()
                .id(1)
                .title("Title")
                .active(true)
                .genre(new BookGenreDTO("Genre"))
                .image("Image")
                .isbn("ISBN")
                .price(new BigDecimal("1.23"))
                .stock(10)
                .parameters(BookParameterMother.createBookParameterDTO())
                .build();
    }

    public static NumberedBookDTO createNumberedBookDTO(int number){
        return new NumberedBookDTO(createBook(), number);
    }
}
