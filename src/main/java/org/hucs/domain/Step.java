package org.hucs.domain;

import java.util.List;

public class Step {
    private String description;
    private List<String> args;

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
