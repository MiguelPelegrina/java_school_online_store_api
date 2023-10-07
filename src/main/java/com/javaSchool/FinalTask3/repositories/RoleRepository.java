package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
