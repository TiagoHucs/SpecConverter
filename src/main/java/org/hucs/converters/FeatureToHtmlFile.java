package org.hucs.converters;

import org.hucs.domain.Feature;
import org.hucs.domain.Scenario;
import org.hucs.domain.Step;
import org.hucs.domain.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeatureToHtmlFile {


    public static void convert(Feature feature,String destinyPath) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html> \n");
        builder.append("<html lang=\"pt-br> \n");
        builder.append("<head> \n");
        builder.append("<meta charset=\"UTF-8\"> \n");
        builder.append("<meta name=\"viewport\" content=\"width = device - width, initial - scale = 1.0\">\n");
        builder.append("<title> " + feature.getName() + " </title>\n");
        builder.append("<style> table, th, td {border:1px solid black; </style>\n");
        builder.append("</head>\n");
        builder.append("<body>\n");
        builder.append("<h1> " + feature.getName() + " </h1>\n");
        builder.append("<p> Esta é uma página HTML gerada com Java.</p>\n");
        generateScenarios(builder,feature.getScenarios());
        builder.append("</body>\n");
        builder.append("</html>\n");

        try (FileWriter escritor = new FileWriter(destinyPath)) {
            escritor.write(builder.toString());
            System.out.println("Arquivo HTML criado com sucesso: " + destinyPath);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    private static void generateScenarios(StringBuilder builder, List<Scenario> scenarios) {
        for (Scenario scenario: scenarios){
            builder.append("<div>Cenario: "+ scenario.getTitle() + "</div>\n");
            generateSteps(builder,scenario.getSteps());
        }
    }

    private static void generateSteps(StringBuilder builder, List<Step> steps) {
        for (Step step: steps){
            if(step.isTableStep()){
                generateTable(builder,step);
            }else {
                builder.append("<a>"+ step.getDescription() + "</a>\n");
            }
        }
    }

    private static void generateTable(StringBuilder builder, Step step) {
        Table table = step.getTable();
        builder.append("<table>\n");

        //headers
        builder.append("<tr>\n");
        for (String title : table.getHeaders()){
            builder.append("<th>"+title+"</th>\n");
        }
        builder.append("</tr>\n");

        //data
        for (List<String> data : table.getData()){
            builder.append("<tr>\n");
            for (String value : data){
                builder.append("<td>"+value+"</td>\n");
            }
            builder.append("</tr>\n");
        }

        builder.append("</table>\n");
    }

    // Método para indentar as linhas HTML
    private static List<String> indentarHTML(List<String> linhas) {
        List<String> linhasIndentadas = new ArrayList<>();
        int nivelIndentacao = 0;

        for (String linha : linhas) {
            // Remove espaços extras no início ou fim da linha
            linha = linha.trim();

            // Verifica se a linha fecha uma tag
            if (linha.startsWith("</")) {
                nivelIndentacao--;
            }

            // Adiciona a linha com a indentação correta
            linhasIndentadas.add("    ".repeat(Math.max(0, nivelIndentacao)) + linha);

            // Verifica se a linha abre uma tag e não fecha na mesma linha
            if (linha.startsWith("<") && !linha.startsWith("</") && !linha.endsWith("/>")) {
                nivelIndentacao++;
            }
        }
        return linhasIndentadas;
    }

}
