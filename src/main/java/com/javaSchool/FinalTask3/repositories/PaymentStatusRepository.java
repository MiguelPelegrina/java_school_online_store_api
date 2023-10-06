package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, String> {
}
