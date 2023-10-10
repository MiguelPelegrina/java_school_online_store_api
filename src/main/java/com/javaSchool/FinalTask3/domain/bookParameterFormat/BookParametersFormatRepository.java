package com.javaSchool.FinalTask3.domain.bookParameterFormat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookParametersFormatRepository extends JpaRepository<BookParametersFormatEntity, String> {
}
