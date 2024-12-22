package org.hucs;

import org.hucs.converters.FileGherkinToFeature;
import org.hucs.domain.Feature;

public class Main {
    public static void main(String[] args) {
        String originPath = "src/main/resources/calculadora.feature";
        String destinyPath = "target";

        Feature feature = FileGherkinToFeature
                .convert(originPath);
        feature.getName();
    }
}