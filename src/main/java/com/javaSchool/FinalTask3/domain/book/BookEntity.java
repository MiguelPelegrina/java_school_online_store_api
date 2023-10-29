package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreEntity;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterEntity;
import jakarta.persistence.CascadeType;
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

    /**
     * TODO Will need a currency entity and a currencyPrice entity, just assuming one → e.g. euro → is not scalable
     *  With Cascade → needs to be created when a book with a new currency is added,
     *  Fields of CurrencyPriceEntity:
     *      id (int) with bookId (1-N relation, bidirectional)
     *      isActive (boolean),
     *      name (string),
     *      price (BigDecimal)
     *  Add another column for Book --> priceId (set<String>)
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // TODO Might move it to parameters
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
    private boolean isActive;

    @Column(name = "image")
    @Lob
    private String image;
}
