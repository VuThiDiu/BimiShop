package com.example.demo.model.dto;

import com.example.demo.model.Image;
import lombok.Data;

@Data
public class ImageDTO {
    private String id;
    private String productId;
    private int tagCategory;
    private int tagColor;

    public static ImageDTO from (Image  image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setProductId(image.getProduct().getId());
        imageDTO.setTagCategory(image.getTagCategory());
        imageDTO.setTagColor(image.getTagColor());
        return imageDTO;
    }
}
