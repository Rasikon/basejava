package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<String> filling;

    public ListSection(List<String> filling) {
        this.filling = filling;
    }

    public List<String> getFilling() {
        return filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(filling, that.filling);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filling);
    }

    @Override
    public String toString() {
        return filling.toString();
    }
}

