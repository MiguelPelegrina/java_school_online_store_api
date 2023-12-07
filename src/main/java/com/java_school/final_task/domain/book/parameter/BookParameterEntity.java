package com.java_school.final_task.domain.book.parameter;

import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "Parameters of a book, shared across multiple books")
@Table(name = "book_parameters", schema = "public", catalog = "online_store")
public class BookParameterEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "author", nullable = false, length = 60)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "format", referencedColumnName = "name", nullable = false)
    private BookParametersFormatEntity format;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
