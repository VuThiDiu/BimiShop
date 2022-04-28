package com.example.demo.controller;


import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.example.demo.model.UploadImage;
import com.example.demo.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload_image")
    public ResponseEntity uploadFile(@RequestBody List<UploadImage> uploadImage) {
        try {
            List<Image> images = new ArrayList<>();
            for (UploadImage image : uploadImage) {
                Image img = new Image(new Product(image.getProductId()), image.getImageUrl(), image.getTagCategory(), image.getTagColor());
                images.add(img);
            }
            imageService.saveAll(images);
            return new ResponseEntity(uploadImage.get(0).getProductId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Can't upload image", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
