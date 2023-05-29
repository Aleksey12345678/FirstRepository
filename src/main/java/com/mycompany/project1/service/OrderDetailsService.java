package com.mycompany.project1.service;

import com.mycompany.project1.domain.OrderDetails;
import com.mycompany.project1.domain.dto.OrderDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderDetailsService {
   List<OrderDetailsDTO> findOrderDetailsByOrderId(Long orderId);
}
