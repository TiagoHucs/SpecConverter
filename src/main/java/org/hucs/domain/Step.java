package org.hucs.domain;

import java.util.List;

public class Step {
    private String description;
    private List<String> args;
    private Table table;

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public void addTable(Table table) {
        this.table = table;
    }

    public boolean isTableStep() {
        return this.table != null;
    }

    public Table getTable() {
        return this.table;
    }

}
