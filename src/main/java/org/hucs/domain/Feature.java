package org.hucs.domain;

import java.util.ArrayList;
import java.util.List;

public class Feature {
    private String name;
    private List<Scenario> scenarios = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Scenario> getScenarios(){
        return this.scenarios;
    }
}
