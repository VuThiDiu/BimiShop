package com.example.demo.service.impl;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
}
