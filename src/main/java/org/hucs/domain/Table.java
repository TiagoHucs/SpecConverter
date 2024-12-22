package org.hucs.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
    List<String> headers = new ArrayList<>();
    List<List<String>> data = new ArrayList<>();

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void addDataLine(List<String> lineData) {
        data.add(lineData);
    }
}
