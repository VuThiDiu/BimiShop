package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @ManyToOne
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JoinColumn(name = "productid")
    private Product product;

    private String urlImage;

    private String tagCategory;

    private String tagColor;

    public Image(Product product, String urlImage, String tagCategory, String tagColor) {
        this.product = product;
        this.urlImage = urlImage;
        this.tagCategory = tagCategory;
        this.tagColor = tagColor;
    }

    public Image() {
    }
}
