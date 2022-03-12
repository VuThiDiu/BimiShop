package com.example.demo.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImage {
    private String imageUrl;
    private String productId;
    private String tagCategory;
    private String tagColor;

}
