package org.hucs.converters;

import org.hucs.domain.Feature;
import org.hucs.domain.Scenario;
import org.hucs.domain.Step;
import org.hucs.domain.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FeatureToHtmlFile {

    static StringBuilder builder = new StringBuilder();
    static int indentLevel = 0;

    public static void convert(Feature feature,String destinyPath) {
        builder = new StringBuilder();
        append("<!DOCTYPE html> ");
        append("<html lang=\"pt-br\"> ");
        append("<head> ");
        append("<meta charset=\"UTF-8\"> ");
        append("<meta name=\"viewport\" content=\"width = device - width, initial - scale = 1.0\">");
        append("<title> " + feature.getName() + " </title>");
        append("<style> table, th, td {border:1px solid black; </style>");
        append("</head>");
        append("<body>");
        append("<h1> " + feature.getName() + " </h1>");
        append("<hr>");
        generateScenarios(builder,feature.getScenarios());
        append("</body>");
        append("</html>");

        try (FileWriter escritor = new FileWriter(destinyPath)) {
            escritor.write(builder.toString());
            System.out.println("Arquivo HTML criado com sucesso: " + destinyPath);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    private static void generateScenarios(StringBuilder builder, List<Scenario> scenarios) {
        for (Scenario scenario: scenarios){
            append("<h2>Cenario: "+ scenario.getTitle() + "</h2>");
            generateSteps(builder,scenario.getSteps());
        }
    }

    private static void generateSteps(StringBuilder builder, List<Step> steps) {
        for (Step step: steps){
            if(step.isTableStep()){
                generateTable(builder,step);
            }else {
                append("<p>"+ step.getDescription() + "</p>");
            }
        }
    }

    private static void generateTable(StringBuilder builder, Step step) {
        Table table = step.getTable();
        append("<table>");

        //headers
        append("<tr>");
        for (String title : table.getHeaders()){
            append("<th>"+title+"</th>");
        }
        append("</tr>");

        //data
        for (List<String> data : table.getData()){
            append("<tr>");
            for (String value : data){
                append("<td>"+value+"</td>");
            }
            append("</tr>");
        }

        append("</table>");
    }

   private static void append(String line){
        configIdent(line);
        builder.append(getIdent() + line + "\n");
   }

    private static void configIdent(String line){
        if(line.contains("<table>")){
            indentLevel++;
        }
        if(line.contains("</table>")){
            indentLevel--;
        }
    }

    private static String getIdent(){
        String tabs = "";
        for(int i = indentLevel; i > 0; i--){
            tabs+= " ";
        }
        return tabs;
    }


}
