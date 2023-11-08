package com.javaSchool.FinalTask3.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Data
@NoArgsConstructor
public abstract class AbstractPageableSortableRequest {
    private String sortType;
    private String sortProperty;
    private int page;
    private int size;

    public void setSortType(String sortType) {
        this.sortType = String.valueOf(DESC.toString().equalsIgnoreCase(this.getSortType()) ? DESC : ASC);
    }
}
