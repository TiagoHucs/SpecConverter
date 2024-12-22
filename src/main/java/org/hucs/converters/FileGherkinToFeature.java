package org.hucs.converters;

import org.hucs.domain.Feature;
import org.hucs.domain.Scenario;
import org.hucs.domain.Step;
import org.hucs.domain.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileGherkinToFeature {

    private FileGherkinToFeature() {
    }

    public static Feature convert(String featureFilePath) {
        Feature feature = new Feature();
        Scenario scenario = null;
        Table table = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(featureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Feature:")) { // Inicia uma feature
                    feature.setName(line.replace("Feature:", "").trim());
                } else if (line.startsWith("Scenario:")) { // Inicia um cenário
                    if (scenario != null) {
                        feature.getScenarios().add(scenario); // Salva o cenário anterior
                    }
                    scenario = new Scenario();
                    scenario.setTitle(line.replace("Scenario:", "").trim());
                } else if (lineStartsWithStepWord(line)) { // Adiciona um step
                    if (scenario == null) {
                        throw new IllegalStateException("Passo encontrado sem cenário associado: " + line);
                    }
                    Step step = new Step();
                    step.setDescription(line);
                    scenario.getSteps().add(step);
                } else if (line.startsWith("|")){
                    if(table == null){
                        table = new Table();
                        table.setHeaders(splitTableData(line));
                    } else {
                        table.addDataLine(splitTableData(line));
                    }
                }
            }

            // Adiciona o último cenário após a leitura
            if (scenario != null) {
                feature.getScenarios().add(scenario);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter .feature para Feature", e);
        }

        System.out.println("Feature " + feature.getName() + " criada!");
        return feature;
    }

    private static boolean lineStartsWithStepWord(String line) {
        return line.startsWith("Given") || line.startsWith("When") || line.startsWith("Then") ||
                line.startsWith("And") || line.startsWith("But");
    }

    private static List<String> splitTableData(String line){
        String[] strings = line.replaceFirst("\\|","").split("\\|");
        List<String> datas = new ArrayList<>();
        for (String data: strings){
            datas.add(data.trim());
        }
        return datas;
    }

}
