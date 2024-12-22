package org.hucs.domain;

public class ScenarioBuilder {
    private Scenario scenario;

    public ScenarioBuilder newScenario(String title){
        this.scenario = new Scenario();
        this.scenario.setTitle(title);
        return this;
    }

    public ScenarioBuilder withStep(Step step){
        this.scenario.getSteps().add(step);
        return this;
    }

    public Scenario build(){
        return this.scenario;
    }

}
