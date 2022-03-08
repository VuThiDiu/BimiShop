package com.example.demo.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
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

    private int discount;

    private double price;

    private DateTime  dateTime;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> imageList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    private long numberOfViews;
}
