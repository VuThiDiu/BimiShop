package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Data
public class User {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    private String phoneNumber;

    private String password;


    // idAddress
    private String defaultAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavProducts> favProducts;
    public User(String id) {
        this.id = id;
    }

    public User() {
    }
}