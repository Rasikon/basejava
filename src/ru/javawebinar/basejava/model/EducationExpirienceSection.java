package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class EducationExpirienceSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<EducationExperience> educationExperiences;

    public EducationExpirienceSection(List<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
    }

    public List<EducationExperience> getEducationExperiences() {
        return educationExperiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationExpirienceSection that = (EducationExpirienceSection) o;
        return Objects.equals(educationExperiences, that.educationExperiences);
    }

    @Override
    public int hashCode() {

        return Objects.hash(educationExperiences);
    }

    @Override
    public String toString() {
        return educationExperiences.toString();
    }
}
