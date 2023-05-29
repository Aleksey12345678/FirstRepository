package com.mycompany.project1.service;


import com.mycompany.project1.domain.Order;
import com.mycompany.project1.domain.OrderStatus;
import com.mycompany.project1.domain.dto.OrderDTO;
import com.mycompany.project1.mapper.OrderMapper;
import com.mycompany.project1.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.MAPPER;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderMapper.fromOrderList(orderRepository.findAll());
    }
    @Override
    public List<OrderDTO> getOrdersByUserId(Long id) {
        return orderMapper.fromOrderList(orderRepository.findAllByUser_Id(id));
    }

    @Override
    public Boolean updateOrderStatus(OrderStatus orderStatus, Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        order.setStatus(orderStatus);
        orderRepository.save(order);
        return true;
    }
}
