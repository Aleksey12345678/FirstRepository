package com.mycompany.project1.repository;


import com.mycompany.project1.domain.Order;
import com.mycompany.project1.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
   List< OrderDetails> findAllByOrderId(Long orderId);
}
