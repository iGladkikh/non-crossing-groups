package com.igladkikh.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<String> lines;

    public Group() {
        lines = new ArrayList<>();
    }

    public  void add(String line) {
        lines.add(line);
    }

    public  int size() {
        return lines.size();
    }

    public List<String> getLines() {
        return lines;
    }
}
