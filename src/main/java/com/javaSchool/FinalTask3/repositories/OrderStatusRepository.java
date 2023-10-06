package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {
}
