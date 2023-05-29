package com.mycompany.project1.mapper;

import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.dto.products.BookDTO;
import com.mycompany.project1.domain.dto.products.EBookDTO;
import com.mycompany.project1.domain.products.Book;
import com.mycompany.project1.domain.products.EBook;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EBookMapper {
    public void setFieldValuesFromDto(EBookDTO book, String fileName, EBook bookDb) {
        bookDb.setBrand(book.getBrand());
        bookDb.setScreenSize(book.getScreenSize());
        bookDb.setPrice(book.getPrice());
        bookDb.setYear(book.getYear());
        bookDb.setTitle("Book " + book.getBrand() + " " + book.getScreenSize());
        if (bookDb.getProductImages() == null) {
            bookDb.setProductImages(Collections.singletonList(fileName));
        } else {
            bookDb.getProductImages().add(fileName);
        }
    }

    public EBook toBook(EBookDTO bookDTO){
       EBook book=new EBook();
       book.setPrice(bookDTO.getPrice());
       book.setYear(bookDTO.getYear());
       book.setProductImages(bookDTO.getProductImages());
       book.setBrand(bookDTO.getBrand());
       book.setScreenSize(bookDTO.getScreenSize());
       book.setTitle("EBook "+bookDTO.getBrand()+" "+bookDTO.getScreenSize());
       return book;
    }
    public EBookDTO fromBook(Product product){
        EBookDTO bookDTO=new EBookDTO();
       EBook book=(EBook)product;
        bookDTO.setPrice(book.getPrice());
        bookDTO.setYear(book.getYear());
        bookDTO.setProductImages(book.getProductImages());
        bookDTO.setBrand(book.getBrand());
        bookDTO.setScreenSize(book.getScreenSize());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setId(book.getId());
        return bookDTO;

    }
    public List<EBookDTO> fromBookList(List<Product> books){
        return books.stream().map(book -> fromBook((EBook)book)).collect(Collectors.toList());

    }
}
