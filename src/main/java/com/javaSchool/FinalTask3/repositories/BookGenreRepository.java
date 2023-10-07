package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, String> {
}
