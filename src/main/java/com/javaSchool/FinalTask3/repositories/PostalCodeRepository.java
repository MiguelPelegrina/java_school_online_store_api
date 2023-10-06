package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, String> {
}
