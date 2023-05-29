package com.mycompany.project1.domain.products;

import com.mycompany.project1.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Book extends Product {

    private String author;
    private String bookTitle;






}

