package com.mycompany.project1.repository;


import com.mycompany.project1.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

//   List<Product> findAllByTitleIsStartingWith(String type);

    @Query(value = "select p.* from products p where p.type  =:type", nativeQuery = true)
    List<Product> findByType(String type);
}
