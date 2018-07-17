package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EducationExperience {
    private Link page;
    private List<Action> actions = new ArrayList<>();


    public EducationExperience(String name, String url, List<Action> actions) {
        this.page = new Link(name, url);
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationExperience that = (EducationExperience) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(page, actions);
    }

    @Override
    public String toString() {
        return "EducationExperience{" +
                "page=" + page +
                ", actions=" + actions +
                '}';
    }
}
