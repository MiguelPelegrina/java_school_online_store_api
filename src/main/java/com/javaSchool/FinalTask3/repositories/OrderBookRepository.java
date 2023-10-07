package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.OrderBook;
import com.javaSchool.FinalTask3.entities.embeddables.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookId> {
}
