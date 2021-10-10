package com.miyabe.app.core;

public enum Commands {
    LEFT("L"),RIGHT("R"),MOVE("M");

    private final String text;

    Commands(final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
