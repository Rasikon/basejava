package ru.javawebinar.basejava.model;

import java.util.List;

public class EducationExpirienceSection extends Section {
    private List<EducationExperience> educationExperiences;

    public EducationExpirienceSection(List<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
    }

    public List<EducationExperience> getEducationExperiences() {
        return educationExperiences;
    }
}
