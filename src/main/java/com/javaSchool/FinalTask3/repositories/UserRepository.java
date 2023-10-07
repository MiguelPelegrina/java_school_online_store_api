package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
