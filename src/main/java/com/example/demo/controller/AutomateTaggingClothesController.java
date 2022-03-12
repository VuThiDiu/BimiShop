package com.example.demo.controller;


import com.example.demo.common.Config;
import com.example.demo.dto.TagResponse;
import com.example.demo.service.IImageService;
import com.example.demo.utils.LoadModel;
import org.apache.http.protocol.HTTP;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.nd4j.linalg.api.ndarray.INDArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/api")
public class AutomateTaggingClothesController {
    @Autowired
    IImageService iImageService;
    LoadModel loadModel = new LoadModel();;
    @PostMapping("/get_tagImage")
        public ResponseEntity<TagResponse>  automateTaggingClothes(@RequestParam("file") MultipartFile file) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
            TagResponse tagResponse = new TagResponse();
        if(file.isEmpty()){
            return new ResponseEntity<TagResponse>(tagResponse, HttpStatus.EXPECTATION_FAILED);
        }else{

            InputStream inputStream = file.getInputStream();
            BufferedImage input = ImageIO.read(inputStream);
            tagResponse = loadModel.prediction(input);
            return new ResponseEntity<TagResponse>(tagResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/create_imageUrl")
        public ResponseEntity<String> create_imageUrl(@RequestParam("file") MultipartFile file){
        try{
            String fileName = iImageService.save(file);
            String imageUrl = iImageService.getImageUrl(fileName);
            return new ResponseEntity<String>(imageUrl, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.toString(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
