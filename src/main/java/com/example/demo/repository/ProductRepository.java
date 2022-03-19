package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
        @Query(value = "select * from product p join image img on p.id = img.productid where (p.discount/100)*p.price between ?2 and ?3 and img.tag_category like ?4 and img.tag_color like ?5 order by ?1 desc", nativeQuery = true)
        List<Product> listProduct ( String sortBy, int costFrom, int costTo, String category, String color);


        @Query(value = "select * from product p join image img on p.id = img.productid where  img.tag_category like ?2 and img.tag_color like ?3 order by ?1 desc", nativeQuery = true)
        List<Product> listProductAllPrice ( String sortBy, String category, String color);

}
