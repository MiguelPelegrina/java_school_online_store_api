package com.javaSchool.FinalTask3.domain.bookGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenreEntity, String> {
}
