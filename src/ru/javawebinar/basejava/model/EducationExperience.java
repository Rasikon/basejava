package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class EducationExperience implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link page;
    private List<Action> actions = new ArrayList<>();

    public EducationExperience() {
    }

    public EducationExperience(String name, String url, List<Action> actions) {
        this.page = new Link(name, url);
        this.actions = actions;
    }

    public Link getPage() {
        return page;
    }

    public List<Action> getActions() {
        return actions;
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
