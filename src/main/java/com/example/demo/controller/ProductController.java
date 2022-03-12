package com.example.demo.controller;


import com.example.demo.dto.UploadProduct;
import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping(value = "/upload_product")
    public ResponseEntity upLoadProduct(@RequestBody UploadProduct uploadProduct){
        try{
            Product product =  Product.from(uploadProduct);
            Product result = productService.saveProduct(product);
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Can't save", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
