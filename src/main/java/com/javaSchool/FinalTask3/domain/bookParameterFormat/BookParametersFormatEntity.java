package com.javaSchool.FinalTask3.domain.bookParameterFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "book_parameter_formats", schema = "public", catalog = "online_store")
public class BookParametersFormatEntity {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;
}
