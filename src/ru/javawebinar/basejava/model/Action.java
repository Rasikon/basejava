package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Action {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String content;

    public Action(LocalDate startDate, LocalDate endDate, String title, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(startDate, action.startDate) &&
                Objects.equals(endDate, action.endDate) &&
                Objects.equals(title, action.title) &&
                Objects.equals(content, action.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startDate, endDate, title, content);
    }

    @Override
    public String toString() {
        return "Action{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
