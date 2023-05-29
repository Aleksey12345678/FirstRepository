package com.mycompany.project1.controller;


import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.dto.BucketDTO;
import com.mycompany.project1.domain.dto.products.ProductDTO;
import com.mycompany.project1.service.BucketService;
import com.mycompany.project1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class BucketController {
    private final BucketService bucketService;
    private final ProductService<Product, ProductDTO> productService;

    public BucketController(BucketService bucketService, ProductService<Product,ProductDTO> productService) {
        this.bucketService = bucketService;
        this.productService = productService;
    }
    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal){
        if(principal==null){
            model.addAttribute("bucket", new BucketDTO());
        }
        else {
            BucketDTO bucketDTO=bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }
    @GetMapping("/toOrder")
    public String commitBucket(Principal principal){
        if(principal!=null){
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }
    @GetMapping("/{id}/remove")
    public String removeFromBucket(@PathVariable Long id, Principal principal){
        if(principal==null){
            return "redirect:/bucket";
        }
        productService.removeFromUserBucket(id,principal.getName());
        return "redirect:/bucket";
    }

}
