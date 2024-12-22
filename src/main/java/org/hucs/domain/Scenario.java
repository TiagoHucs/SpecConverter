package org.hucs.domain;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String title;
    private List<Step> steps = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
