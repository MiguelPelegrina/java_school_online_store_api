package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderBookEntity} identified by an
 * ID with the embeddable {@link OrderBookId}.
 */
@Repository
public interface OrderBookRepository extends JpaRepository<OrderBookEntity, OrderBookId> {
}
