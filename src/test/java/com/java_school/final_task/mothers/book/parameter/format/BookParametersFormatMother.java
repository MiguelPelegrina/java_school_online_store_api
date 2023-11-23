package com.java_school.final_task.mothers.book.parameter.format;

import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;

public class BookParametersFormatMother {
    public static BookParametersFormatEntity createBookParametersFormat(){
        return BookParametersFormatEntity.builder()
                .name("Format")
                .build();
    }
}
