package com.mycompany.project1.domain.dto;

import com.mycompany.project1.domain.Order;
import com.mycompany.project1.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO {

    private Order order;
    private Product product;
    private BigDecimal amount;
    private BigDecimal price;




}

