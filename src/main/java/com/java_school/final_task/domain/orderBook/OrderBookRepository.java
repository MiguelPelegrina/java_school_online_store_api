package com.java_school.final_task.domain.orderBook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderBookEntity} identified by an
 * ID with Integer values.
 */
@Repository
public interface OrderBookRepository extends JpaRepository<OrderBookEntity, Integer>{}
