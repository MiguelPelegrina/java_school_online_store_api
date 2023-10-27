package com.javaSchool.FinalTask3.domain.userRole;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<List<UserRoleEntity>> findByUser(UserEntity user);
}
