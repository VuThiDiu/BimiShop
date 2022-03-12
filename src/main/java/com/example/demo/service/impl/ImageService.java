package com.example.demo.service.impl;


import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image saveImage(Image image){
        return imageRepository.save(image);
    }
}
