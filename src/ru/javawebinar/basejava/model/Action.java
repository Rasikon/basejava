package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Action implements Serializable {
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String content;

    public Action() {
    }

    public Action(LocalDate startDate, LocalDate endDate, String title, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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
