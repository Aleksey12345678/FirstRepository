package com.mycompany.project1.mapper;

import com.mycompany.project1.domain.OrderDetails;
import com.mycompany.project1.domain.dto.OrderDetailsDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    OrderDetailMapper MAPPER= Mappers.getMapper(OrderDetailMapper.class);
    OrderDetails toProduct(OrderDetailsDTO dto);
    @InheritInverseConfiguration
    OrderDetailsDTO fromProduct(OrderDetails orderDetails);
    List<OrderDetails> toOrderDetailsList(List<OrderDetailsDTO> orderDetailsDTOS);
    List<OrderDetailsDTO> fromOrderDetailsList (List<OrderDetails> orderDetails);
}
