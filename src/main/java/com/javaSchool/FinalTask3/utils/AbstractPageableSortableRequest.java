package com.javaSchool.FinalTask3.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Abstract class that contains all attributes and methods shared between request to pageable and sortable data.
 */
@Data
@NoArgsConstructor
public abstract class AbstractPageableSortableRequest {
    private String sortType = DESC.toString();
    private String sortProperty = "id";
    private int page = 0;
    private int size = 20;

    public void setSortType(String sortType) {
        this.sortType = String.valueOf(DESC.toString().equalsIgnoreCase(sortType) ? DESC : ASC);
    }
}
