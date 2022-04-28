package com.example.demo.model;
import lombok.Data;

@Data
public class UploadImage {
    private String imageUrl;
    private String productId;
    private String tagCategory;
    private String tagColor;

}