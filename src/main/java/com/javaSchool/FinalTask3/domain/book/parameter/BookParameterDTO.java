package com.javaSchool.FinalTask3.domain.book.parameter;

import com.javaSchool.FinalTask3.domain.book.parameter.format.BookParametersFormatDTO;
import lombok.Data;

@Data
public class BookParameterDTO {
    private int id;
    private String author;
    private BookParametersFormatDTO format;
    private boolean isActive;
}
