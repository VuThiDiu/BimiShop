package com.example.demo.model.dto;

import com.example.demo.model.Image;
import lombok.Data;

@Data
public class ImageDTO {
    private String id;
    private String productId;
    private String urlImage;
    private String tagCategory;
    private String tagColor;

    public static ImageDTO from (Image  image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setProductId(image.getProduct().getId());
        imageDTO.setUrlImage(image.getUrlImage());
        imageDTO.setTagCategory(image.getTagCategory());
        imageDTO.setTagColor(image.getTagColor());
        return imageDTO;
    }
}
