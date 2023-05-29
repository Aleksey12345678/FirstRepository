package com.mycompany.project1.mapper;

import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.dto.products.BookDTO;
import com.mycompany.project1.domain.products.Book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public void setFieldValuesFromDto(BookDTO book, String fileName, Book bookDb) {
        bookDb.setBookTitle(book.getBookTitle());
        bookDb.setAuthor(book.getAuthor());
        bookDb.setPrice(book.getPrice());
        bookDb.setYear(book.getYear());
        bookDb.setTitle("Book " + book.getBookTitle() + " " + book.getAuthor());
        if (bookDb.getProductImages() == null) {
            bookDb.setProductImages(Collections.singletonList(fileName));
        } else {
            bookDb.getProductImages().add(fileName);
        }
    }


    public BookDTO fromBook(Product product){
        BookDTO bookDTO=new BookDTO();
        Book book=(Book)product;
        bookDTO.setPrice(book.getPrice());
        bookDTO.setYear(book.getYear());
        bookDTO.setProductImages(book.getProductImages());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setId(book.getId());
        return bookDTO;

    }
    public List<BookDTO> fromBookList(List<Product> books){
        return books.stream().map(book -> fromBook((Book)book)).collect(Collectors.toList());

    }
}
