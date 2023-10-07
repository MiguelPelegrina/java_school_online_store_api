package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.BookParametersFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookParameterFormatRepository extends JpaRepository<BookParametersFormat, String> {
}
