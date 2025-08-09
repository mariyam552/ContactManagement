package com.contactManagementBackend.contactManagementBackend.Repository;

import com.contactManagementBackend.contactManagementBackend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface userRepository extends JpaRepository<user, Long> {
    Optional<user> findByEmail(String email);
}
