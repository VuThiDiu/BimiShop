package com.example.demo.service.impl;


import com.example.demo.dto.SearchForm;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> listProduct(String sortBy, SearchForm searchForm){
        if(searchForm.getCostTo() !=  null && searchForm.getCostTo() != 0) {
            return productRepository.listProduct(sortBy, searchForm.getCostFrom(), searchForm.getCostTo(), searchForm.getCategory(), searchForm.getColor());
        }else{
            return productRepository.listProductAllPrice(sortBy, searchForm.getCategory(), searchForm.getColor());
        }
    }
    public Product getProduct(String id){
        return productRepository.getById(id);
    }

    public List<Product> getProductsByUserID(String id){
        return productRepository.getProductsByUserID(id);
    }

    public void updateViewProductById(String id) {
        Product product = productRepository.findById(id).orElse( new Product());
        product.setNumberOfViews(product.getNumberOfViews() + 1);
        productRepository.save(product);
    }
}
