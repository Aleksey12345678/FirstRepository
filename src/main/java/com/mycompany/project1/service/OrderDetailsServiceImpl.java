package com.mycompany.project1.service;

import com.mycompany.project1.domain.OrderDetails;
import com.mycompany.project1.domain.dto.OrderDetailsDTO;
import com.mycompany.project1.mapper.OrderDetailMapper;
import com.mycompany.project1.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailMapper orderDetailMapper=OrderDetailMapper.MAPPER;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetailsDTO> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailMapper.fromOrderDetailsList(orderDetailsRepository.findAllByOrderId(orderId));
    }
}
