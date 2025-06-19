package com.igladkikh.model;

public class Position {
    private final String line;
    private final int fragmentNumber;

    public Position(String line, int fragmentNumber) {
        this.line = line;
        this.fragmentNumber = fragmentNumber;
    }

    public String getLine() {
        return line;
    }

    public int getFragmentNumber() {
        return fragmentNumber;
    }
}
