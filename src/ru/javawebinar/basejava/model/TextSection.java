package ru.javawebinar.basejava.model;

public class TextSection extends Section {
    private String filling;

    public TextSection(String filling) {
        this.filling = filling;
    }

    public String getFilling() {
        return filling;
    }

    @Override
    public String toString() {
        return filling;
    }
}
