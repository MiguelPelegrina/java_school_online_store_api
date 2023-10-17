package com.javaSchool.FinalTask3.domain.bookParameter;

import com.javaSchool.FinalTask3.domain.bookParameterFormat.BookParametersFormatDTO;
import lombok.Data;

@Data
public class BookParameterDTO {
    private int id;
    private String author;
    private BookParametersFormatDTO format;
    private boolean isActive;
}
