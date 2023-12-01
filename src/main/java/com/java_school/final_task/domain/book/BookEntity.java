package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "books", schema = "public", catalog = "online_store")
public class BookEntity {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre", referencedColumnName = "name", nullable = false)
    private BookGenreEntity genre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "parameters_id", referencedColumnName = "id", nullable = false)
    private BookParameterEntity parameters;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "image")
    @Lob
    private String image;
}
