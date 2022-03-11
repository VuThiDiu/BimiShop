package com.example.demo.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    private DateTime orderDate;

    private DateTime shippedDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private String address;

    private int discount;
}
