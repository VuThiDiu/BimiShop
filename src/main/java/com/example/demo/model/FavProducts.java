package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favProduct")
@Data
public class FavProducts {
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
            name = "fav_products",
            joinColumns = @JoinColumn(name = "fav_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> favProducts;
}
