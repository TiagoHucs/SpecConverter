package org.hucs.converters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GherkinFileToLines {

    public static List<String> convert(String featureFilePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(featureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                lines.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter .feature para lista de linhas", e);
        }
        System.out.println("Lista de linhas criada!");
        return lines;
    }
}
