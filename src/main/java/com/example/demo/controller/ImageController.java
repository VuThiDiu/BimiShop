package com.example.demo.controller;

import com.example.demo.dto.UploadImage;
import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.example.demo.service.IImageService;
import com.example.demo.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/upload_image")
    public ResponseEntity uploadFile(@RequestBody UploadImage uploadImage) {
            try {
                Image img = new Image(new Product(uploadImage.getProductId()),uploadImage.getImageUrl(),  uploadImage.getTagCategory(), uploadImage.getTagColor());
                imageService.saveImage(img);
                return new ResponseEntity("Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity("Can't upload image", HttpStatus.EXPECTATION_FAILED);
            }
    }

}
