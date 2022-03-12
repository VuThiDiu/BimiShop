package com.example.demo.model.dto;


import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.google.api.client.util.DateTime;
import lombok.Data;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private String id;
    private String userId;
    private int quantityInStock;
    private int discount;
    private double price;
    private DateTime dateTime;
    private List<ImageDTO> images;
    private List<OrderDetailDTO> orderDetails;
    private long numberOfViews;
    private long numberOfSold;
    private String address;
    private String description;
    private Set<String> tagCategories;
    private Set<String> tagColors;
    private double cost;

    public static ProductDTO from(Product product){
        ProductDTO  productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setUserId(product.getUser().getId());
        productDTO.setQuantityInStock(product.getQuantityInStock());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setPrice(product.getPrice());
        productDTO.setDateTime(product.getDateTime());
        productDTO.setImages(product.getImageList().stream().map(ImageDTO::from).collect(Collectors.toList()));
        productDTO.setOrderDetails(product.getOrderDetails().stream().map(OrderDetailDTO::from).collect(Collectors.toList()));
        productDTO.setNumberOfViews(product.getNumberOfViews());
        productDTO.setAddress(product.getAddress());
        productDTO.setDescription(product.getDescription());
        Set<String> tagCategories = new HashSet<>();
        Set<String> tagColors = new HashSet<>();
        for(Image image : product.getImageList()){
            tagCategories.add(image.getTagCategory());
            tagColors.add(image.getTagColor());
        }
        productDTO.setTagCategories(tagCategories);
        productDTO.setTagColors(tagColors);
        productDTO.setCost(product.getPrice()*( 1 - (productDTO.getDiscount()/10)));

        return productDTO;
    }
}
