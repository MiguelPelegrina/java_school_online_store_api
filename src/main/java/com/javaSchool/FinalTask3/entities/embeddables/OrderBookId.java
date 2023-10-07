package com.javaSchool.FinalTask3.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@RequiredArgsConstructor
public class OrderBookId implements Serializable {
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "book_id", nullable = false)
    private int bookId;
}
