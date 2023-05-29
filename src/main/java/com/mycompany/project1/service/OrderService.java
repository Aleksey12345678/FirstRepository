package com.mycompany.project1.service;


import com.mycompany.project1.domain.Order;
import com.mycompany.project1.domain.OrderStatus;
import com.mycompany.project1.domain.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    List<OrderDTO> getOrders();
    public Boolean updateOrderStatus(OrderStatus orderStatus, Long id);
    public List<OrderDTO> getOrdersByUserId(Long id);
}
