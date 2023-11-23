package com.java_school.final_task.mothers.book.genre;

import com.java_school.final_task.domain.book.genre.BookGenreEntity;

// TODO Randomize values
public class BookGenreMother {
    public static BookGenreEntity createBookGenre(){
        return BookGenreEntity.builder()
                .name("Genre")
                .build();
    }
}
