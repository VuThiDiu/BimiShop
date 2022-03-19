package com.example.demo.model;


import com.example.demo.dto.UploadProduct;
import com.google.api.client.util.DateTime;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    private int quantityInStock;

    private String title;

    private String description;

    private int discount;

    private double price;

    private DateTime dateTime;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> imageList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private long numberOfViews;

    private long numberOfSold;

    private String address;

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }
    public static Product from (UploadProduct uploadProduct){
        Product product = new Product();
        product.setUser(new User(uploadProduct.getUserId()));
        product.setQuantityInStock(uploadProduct.getQuantityInStock());
        product.setPrice(uploadProduct.getPrice());
        product.setDateTime(uploadProduct.getDateTime());
        product.setDiscount(uploadProduct.getDiscount());
        product.setAddress(uploadProduct.getAddress());
        product.setDescription(uploadProduct.getDescription());
        product.setTitle(uploadProduct.getTitle());
        return product;
    }

}
