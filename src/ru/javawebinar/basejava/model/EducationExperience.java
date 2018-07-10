package ru.javawebinar.basejava.model;

import java.time.LocalDate;

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

}
