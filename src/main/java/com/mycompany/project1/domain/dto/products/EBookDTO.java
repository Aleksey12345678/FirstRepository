package com.mycompany.project1.domain.dto.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EBookDTO extends ProductDTO{
    private String brand;
    private Integer screenSize;



    }

