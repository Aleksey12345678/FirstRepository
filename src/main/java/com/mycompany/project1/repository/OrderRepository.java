package com.mycompany.project1.repository;


import com.mycompany.project1.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser_Id(Long id);

}
