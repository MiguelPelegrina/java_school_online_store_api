package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.BookParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookParameterRepository extends JpaRepository<BookParameter, Integer> {
}
