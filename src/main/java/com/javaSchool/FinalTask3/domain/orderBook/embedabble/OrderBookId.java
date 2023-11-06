package com.javaSchool.FinalTask3.domain.orderBook.embedabble;

import com.javaSchool.FinalTask3.domain.orderBook.OrderBookEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Embeddable ID of {@link OrderBookEntity}. Works as composite key of the entity. Contains an ID of the order and
 * another ID of the book. That way each order can only have one book, although the amount of it can vary.
 */
@Deprecated
@AllArgsConstructor
@Builder
@Data
@Embeddable
@RequiredArgsConstructor
public class OrderBookId implements Serializable {
    // TODO Not sure if right
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "book_id", nullable = false)
    private int bookId;
}
