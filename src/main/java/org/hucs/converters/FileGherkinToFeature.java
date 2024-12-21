package org.hucs.converters;

import org.hucs.domain.Feature;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileGherkinToFeature {

    private FileGherkinToFeature(){}

    public static Feature convert(String featureFilePath){
        Feature feature = new Feature();
        try (BufferedReader reader = new BufferedReader(new FileReader(featureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Feature:")) {
                    feature.setName(line.replace("Feature:", "").trim());
                }
            }
        } catch (Exception e){
            throw new RuntimeException("Erro ao converter .feature par Feature", e);
        }
        System.out.println("Feature " + feature.geName() + " criada!");
        return feature;
    }
}
