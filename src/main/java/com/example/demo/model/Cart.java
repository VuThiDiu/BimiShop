package com.example.demo.model;

import lombok.Data;
import org.datavec.api.transform.transform.string.ChangeCaseStringTransform;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productsInCart;

}
