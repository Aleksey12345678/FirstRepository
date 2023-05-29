package com.mycompany.project1.service;



import com.mycompany.project1.domain.Message;
import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.products.BookDTO;
import com.mycompany.project1.domain.dto.products.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService<T extends Product,S extends ProductDTO > {
    List<Product> getAll(String type);
    void addToUserBucket(Long productId, String username);
    void removeFromUserBucket(Long productId, String username);

    void updateProduct(Product product);
    Product getProductById(Long Id);

    Boolean saveNewProduct(Product product);
    public String getProductTypeById(Long id);
    void saveMessage(Message message, MultipartFile file, String uploadPath, User user, Long id) throws IOException;
    public List<Message> getMessagesByProductId(Long id);
    String getResultFilename(MultipartFile file, String uploadPath) throws IOException;
}
