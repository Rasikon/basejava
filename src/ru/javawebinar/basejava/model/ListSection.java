package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {
    private List<String> filling;

    public ListSection(List<String> filling) {
        this.filling = filling;
    }

    public List<String> getFilling() {
        return filling;
    }

}
