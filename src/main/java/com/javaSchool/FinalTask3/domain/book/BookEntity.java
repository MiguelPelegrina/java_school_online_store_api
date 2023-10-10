package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.bookParameter.BookParameterEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Entity of the Book that is stored in the "books" table in the database.
 */
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

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre", referencedColumnName = "name", nullable = false)
    private BookGenreEntity genre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parameters_id", referencedColumnName = "id", nullable = false)
    private BookParameterEntity parameters;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "image", nullable = false)
    @Lob
    // TODO Not sure if right
    private byte[] image;
}
