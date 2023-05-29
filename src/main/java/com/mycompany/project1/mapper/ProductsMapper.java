//package com.mycompany.project1.mapper;
//
//import com.mycompany.project1.domain.Product;
//import com.mycompany.project1.domain.dto.products.BookDTO;
//import com.mycompany.project1.domain.products.Book;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ProductsMapper <T>{
//
//
//
//    public T toBook(PrductDTO prductDTODTO){
//        T product=(T)new Product();
//
//       book.setPrice(bookDTO.getPrice());
//       book.setYear(bookDTO.getYear());
//       book.setProductImages(bookDTO.getProductImages());
//       book.setAuthor(bookDTO.getAuthor());
//       book.setBookTitle(bookDTO.getBookTitle());
//       book.setTitle("Book "+bookDTO.getAuthor()+" "+bookDTO.getBookTitle());
//       return book;
//    }
//    public BookDTO fromBook(Product product){
//        BookDTO bookDTO=new BookDTO();
//        Book book=(Book)product;
//        bookDTO.setPrice(book.getPrice());
//        bookDTO.setYear(book.getYear());
//        bookDTO.setProductImages(book.getProductImages());
//        bookDTO.setAuthor(book.getAuthor());
//        bookDTO.setBookTitle(book.getBookTitle());
//        bookDTO.setTitle(book.getTitle());
//        bookDTO.setId(book.getId());
//        return bookDTO;
//
//    }
//    public List<BookDTO> fromBookList(List<Product> books){
//        return books.stream().map(book -> fromBook((Book)book)).collect(Collectors.toList());
//
//    }
//}
