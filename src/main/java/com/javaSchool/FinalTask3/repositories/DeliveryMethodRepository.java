package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.DeliveryMethod;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodRepository extends JpaAttributeConverter<DeliveryMethod, String> {
}
