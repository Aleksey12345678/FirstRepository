package com.mycompany.project1.service;


import com.mycompany.project1.domain.*;
import com.mycompany.project1.domain.dto.BucketDTO;
import com.mycompany.project1.domain.dto.BucketDetailDTO;
import com.mycompany.project1.repository.BucketRepository;
import com.mycompany.project1.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    private final OrderService orderService;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserServiceImpl userService, OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productsIds) {
        Bucket bucket=new Bucket();
        bucket.setUser(user);
        bucket.setProducts(convertProductsIdToReference(productsIds));
        bucketRepository.save(bucket);
        return bucket;
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products=bucket.getProducts();
        List<Product> newProductList=products==null?new ArrayList<>():new ArrayList<>(products);
        newProductList.addAll(convertProductsIdToReference(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public void removeProducts(Bucket bucket, Long productId) {
       List <Product> newList=bucket.getProducts().stream().filter(product -> product.getId()!=productId).collect(Collectors.toList());
       bucket.setProducts(newList);
        bucketRepository.save(bucket);

    }


    @Override
    public BucketDTO getBucketByUser(String name) {
        User user=userService.findByUsername(name);
        if(user==null||user.getBucket()==null){
            return new BucketDTO();
        }
        BucketDTO bucketDTO =new BucketDTO();
        Map <Long, BucketDetailDTO>  mapByProductId=new HashMap<>();

        List <Product> products=user.getBucket().getProducts();
        for(Product product: products){
            BucketDetailDTO detail=mapByProductId.get(product.getId());
            if(detail==null){
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            }else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum()+Double.valueOf(product.getPrice().toString()));
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }
    @Override
    @Transactional
    public void commitBucketToOrder(String username){
        User user=userService.findByUsername(username);
        if(user==null){
            throw new RuntimeException("user is not found");
        }
        Bucket bucket=user.getBucket();
        if(bucket==null||bucket.getProducts().isEmpty()){
            return ;
        }
        Order order=new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Product, Long> productWithAmount=bucket.getProducts().stream()
                .collect(Collectors.groupingBy(product -> product,Collectors.counting()));
        List<OrderDetails> orderDetails=productWithAmount.entrySet().stream()
                .map(pair-> new OrderDetails(order,pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());
        BigDecimal total=new BigDecimal(orderDetails.stream()
                .map(details -> details.getPrice().multiply(details.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetails);
        order.setSum(total);
        order.setAddress("mone");
        orderService.saveOrder(order);
        bucket.getProducts().clear();
        bucketRepository.save(bucket);
    }

    private List<Product> convertProductsIdToReference(List<Long> productIds){
        List<Product> productList=productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
        return productList;
    }

}
