package com.example.demo.controller;


import com.example.demo.common.Config;
import com.example.demo.dto.TagResponse;
import com.example.demo.utils.LoadModel;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.nd4j.linalg.api.ndarray.INDArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AutomateTaggingClothesController {
    public List<String> tagCategory = Arrays.asList("Quần Dài", "Quần Short", "Váy Liền", "Áo Phông", "Áo Sơ Mi", "Áo Nỉ", "Áo Khoác");
    public List<String> tagColor = Arrays.asList("white", "pink", "red", "orange", "yellow", "blue", "gray", "black");

    LoadModel loadModel = new LoadModel();;
    @PostMapping("/upload_image")
        public ResponseEntity<TagResponse>  automateTaggingClothes(@RequestParam("file") MultipartFile file) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
            TagResponse tagResponse = new TagResponse();
        if(file.isEmpty()){
            return new ResponseEntity<TagResponse>(tagResponse, HttpStatus.EXPECTATION_FAILED);
        }else{
            InputStream inputStream = file.getInputStream();
            BufferedImage input = ImageIO.read(inputStream);
            int predictionForCategory = loadModel.predictionForCategory(input);
            int predictionForColor = loadModel.predictionForColor(input);
            tagResponse.setTagCategory(tagCategory.get(predictionForCategory));
            tagResponse.setTagColor(tagColor.get(predictionForColor));
            return new ResponseEntity<TagResponse>(tagResponse, HttpStatus.OK);
        }

    }
}
