package org.hucs.converters;

import org.hucs.domain.Feature;
import org.hucs.domain.Scenario;
import org.hucs.domain.Step;
import org.hucs.domain.Table;

import java.util.ArrayList;
import java.util.List;

public class LinesToFeature {

    static Feature feature = new Feature();
    static Scenario scenario = null;
    static Table table = null;

    private LinesToFeature() {
    }

    public static Feature convert(List<String> lines) {

        for (String line : lines) {
            line = line.trim();
            processLineFeature(line);
            processlineScenario(line);
            processLineStep(line);
            processLineTable(line);
        }

        // Adiciona o último cenário após a leitura
        if (scenario != null) {
            feature.getScenarios().add(scenario);
        }

        System.out.println("Feature " + feature.getName() + " criada!");
        return feature;
    }

    private static void closeTableStep() {
        Step tableStep = new Step();
        tableStep.setDescription("TABLE STEP");
        tableStep.addTable(table);
        scenario.getSteps().add(tableStep);
        table = null;
    }

    private static void processLineTable(String line) {
        if (line.startsWith("|")) {
            if (table == null) {
                table = new Table();
                table.setHeaders(splitTableData(line));
            } else {
                table.addDataLine(splitTableData(line));
            }
        } else if (!line.startsWith("|") && table != null) {
            closeTableStep();
        }
    }

    private static void processLineStep(String line) {
        if (lineStartsWithStepWord(line)) {
            if (scenario == null) {
                throw new IllegalStateException("Passo encontrado sem cenário associado: " + line);
            }
            Step step = new Step();
            step.setDescription(line);
            scenario.getSteps().add(step);
        }
    }

    private static void processlineScenario(String line) {
        if (line.startsWith("Scenario:")) {
            if (scenario != null) {
                feature.getScenarios().add(scenario); // Salva o cenário anterior
            }
            scenario = new Scenario();
            scenario.setTitle(line.replace("Scenario:", "").trim());
        }
    }

    private static void processLineFeature(String line) {
        if (line.startsWith("Feature:")) {
            feature.setName(line.replace("Feature:", "").trim());
        }
    }

    private static boolean lineStartsWithStepWord(String line) {
        return line.startsWith("Given") || line.startsWith("When") || line.startsWith("Then") ||
                line.startsWith("And") || line.startsWith("But");
    }

    private static List<String> splitTableData(String line) {
        String[] strings = line.replaceFirst("\\|", "").split("\\|");
        List<String> datas = new ArrayList<>();
        for (String data : strings) {
            datas.add(data.trim());
        }
        return datas;
    }

}
