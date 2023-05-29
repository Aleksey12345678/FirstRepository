package com.mycompany.project1.domain.dto.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends ProductDTO{
    private String author;
    private String bookTitle;



    }

