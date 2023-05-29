package com.mycompany.project1.service;

import com.mycompany.project1.domain.Bucket;
import com.mycompany.project1.domain.Message;
import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.products.ProductDTO;


import com.mycompany.project1.repository.MessageRepository;
import com.mycompany.project1.repository.ProductRepos;
import com.mycompany.project1.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl<T extends Product, S extends ProductDTO> implements ProductService<Product, ProductDTO> {
    // private final ProductMapper mapper = ProductMapper.MAPPER;
    // private final BookMapper bookMapper=new BookMapper();

    private final ProductRepository productRepository;
    private final MessageRepository messageRepository;
    private final UserServiceImpl userService;
    private final BucketService bucketService;
    private final ProductRepos productRepos;


    public ProductServiceImpl(ProductRepository productRepository, MessageRepository messageRepository, UserServiceImpl userService, BucketService bucketService, ProductRepos productRepos) {
        this.productRepository = productRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.productRepos = productRepos;
    }

    @Override
    public List<Product> getAll(String type) {
        List<Product> products = productRepository.findByType(type);

        return products;
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found- " + username);
        }
        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
            bucketService.addProducts(newBucket, Collections.singletonList(productId));
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @Transactional
    public void removeFromUserBucket(Long productId, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found- " + username);
        }
        Bucket bucket = user.getBucket();

        bucketService.removeProducts(bucket, productId);

    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean saveNewProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public String getProductTypeById(Long id) {
        return productRepos.getType(id).toLowerCase();
    }

    @Override
    public void saveMessage(Message message, MultipartFile file, String uploadPath, User user, Long id) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String resultFilename = getResultFilename(file, uploadPath);
            message.setFilename(resultFilename);
        }
        message.setAuthor(user);
        message.setProduct(getProductById(id));
        messageRepository.save(message);
    }


    @Override
    public String getResultFilename(MultipartFile file, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }

    public List<Message> getMessagesByProductId(Long id) {
        return messageRepository.findAllByProductId(id);
    }
}


