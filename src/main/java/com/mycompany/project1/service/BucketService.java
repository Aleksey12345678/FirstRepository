package com.mycompany.project1.service;



import com.mycompany.project1.domain.Bucket;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productsIds);
    void addProducts(Bucket bucket,List<Long> productIds);
    void removeProducts(Bucket bucket, Long productId);
    BucketDTO getBucketByUser(String name);
    void commitBucketToOrder(String username);


}
