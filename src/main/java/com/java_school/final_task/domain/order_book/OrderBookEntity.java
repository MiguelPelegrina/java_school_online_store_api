package com.java_school.final_task.domain.order_book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.order.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "order_books", schema = "public", catalog = "online_store")
public class OrderBookEntity {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    private BookEntity book;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBookEntity other = (OrderBookEntity) o;
        return getId() != 0 && getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
