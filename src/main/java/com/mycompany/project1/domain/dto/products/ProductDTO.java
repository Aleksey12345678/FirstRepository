package com.mycompany.project1.domain.dto.products;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private Long id;
    private BigDecimal price;
    private Integer year;
    private List <String> productImages;
    private String title;

    public ProductDTO(Long id, BigDecimal price, Integer year, String title) {
        this.id = id;
        this.price = price;
        this.year = year;
        this.title = title;
    }
}
