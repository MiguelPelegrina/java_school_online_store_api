package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Schema(description = "A book that be bought be a user through a order")
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre", referencedColumnName = "name", nullable = false)
    @ToString.Exclude
    private BookGenreEntity genre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "parameters_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private BookParameterEntity parameters;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "image")
    @Lob
    private String image;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity other = (BookEntity) o;
        return getId() != 0 && getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
