package com.example.demo.utils;
import com.example.demo.common.Config;
import org.datavec.image.loader.ImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.api.ndarray.INDArray;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LoadModel {
    public INDArray loadModel(BufferedImage image) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        String clothesClassification = new ClassPathResource(
                "/static/category.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(clothesClassification );

        // make a randomSimple
        ImageLoader loader = new ImageLoader( Config.IMAGE_WIDTH.getValue(), Config.IMAGE_HEIGHT.getValue(), Config.CHANNELS.getValue());
        //
        INDArray input = loader.asMatrix(image).div(255.0f);
        // get  prediction
        return   model.output(input);
    }

}
