package com.mycompany.project1.service;

import com.mycompany.project1.domain.Bucket;
import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.repository.MessageRepository;
import com.mycompany.project1.repository.ProductRepos;
import com.mycompany.project1.repository.ProductRepository;

import com.mycompany.project1.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private BucketService bucketService;
    @Mock
    private ProductRepos productRepos;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    void getAll() {
        Mockito.doReturn(List.of(new Product(), new Product()))
                .when(productRepository).findByType("book");
        ;
        Assert.assertFalse(productService.getAll("book").isEmpty());
        Mockito.verify(productRepository, Mockito.times(1)).findByType("book");

    }


    @Test
    void removeFromUserBucket() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void saveNewProduct() {
    }

    @Test
    void getProductTypeById() {
    }

    @Test
    void saveMessage() {
    }

    @Test
    void getResultFilename() {
    }

    @Test
    void getMessagesByProductId() {
    }
}