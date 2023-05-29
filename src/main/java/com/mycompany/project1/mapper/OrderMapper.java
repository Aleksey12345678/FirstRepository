package com.mycompany.project1.mapper;

import com.mycompany.project1.domain.Order;
import com.mycompany.project1.domain.dto.OrderDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER= Mappers.getMapper(OrderMapper.class);
    Order toOrder(OrderDTO dto);
    @InheritInverseConfiguration
    OrderDTO fromOrder(Order order);
    List<Order> toOrderList(List<OrderDTO> orderDTOS);
    List<OrderDTO> fromOrderList (List<Order> orders);
}
