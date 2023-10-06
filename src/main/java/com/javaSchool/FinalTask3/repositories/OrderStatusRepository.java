package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.OrderStatus;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaAttributeConverter<OrderStatus, String> {
}
