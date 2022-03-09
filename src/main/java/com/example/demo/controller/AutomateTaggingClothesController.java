package com.example.demo.controller;


import com.example.demo.common.Config;
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
    LoadModel loadModel = new LoadModel();;
    @PostMapping("/upload_image")
        public ResponseEntity<String>  automateTaggingClothes(@RequestParam("file") MultipartFile file) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        if(file.isEmpty()){
            return new ResponseEntity<String>("Can't upload the file", HttpStatus.EXPECTATION_FAILED);
        }else{
            InputStream inputStream = file.getInputStream();
//            BufferedImage input = new BufferedImage(Config.IMAGE_WIDTH.getValue(), Config.IMAGE_HEIGHT.getValue(),BufferedImage.TYPE_INT_RGB);
            BufferedImage input = ImageIO.read(inputStream);
            int prediction = loadModel.loadModel(input);
            return new ResponseEntity<String>(String.valueOf(prediction), HttpStatus.OK);
        }

    }
}
