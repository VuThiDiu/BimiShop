package com.example.demo.controller;

import com.example.demo.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api")
public class ImageController {
    @Autowired
    IImageService imageService;

    @PostMapping("/upload_image")
    public ResponseEntity uploadFile(@RequestParam(name = "file") MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                String fileName = imageService.save(file);
                String imageUrl = imageService.getImageUrl(fileName);
                System.out.println(imageUrl);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return ResponseEntity.ok().build();
    }
}
