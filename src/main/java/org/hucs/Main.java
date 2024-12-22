package org.hucs;

import org.hucs.converters.FeatureToHtmlFile;
import org.hucs.converters.LinesToFeature;
import org.hucs.converters.GherkinFileToLines;
import org.hucs.domain.Feature;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String originPath = "src/main/resources/calculadora.feature";
        String destinyPath = "target/calculadora.html";

        List<String> lines = GherkinFileToLines.convert(originPath);
        Feature feature = LinesToFeature.convert(lines);
        FeatureToHtmlFile.convert(feature,destinyPath);
        feature.getName();

    }
}