package com.mycompany.project1.repository;

import com.mycompany.project1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
    User findFirstByUsername(String name);
}
