package com.javaSchool.FinalTask3.domain.book.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NumberedBookDTO extends BookDTO {
    private int totalAmount;
}
