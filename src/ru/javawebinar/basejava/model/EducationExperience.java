package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class EducationExperience {
    private Link page;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String content;

    public EducationExperience(String name, String url, LocalDate startDate, LocalDate endDate, String title, String content) {
        this.page = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationExperience that = (EducationExperience) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(page, startDate, endDate, title, content);
    }

    @Override
    public String toString() {
        return "EducationExperience{" +
                "page=" + page +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
