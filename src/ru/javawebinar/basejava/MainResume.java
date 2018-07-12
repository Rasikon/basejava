package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainResume {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"");
        list.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов");

        List<String> listok = new ArrayList<String>();
        listok.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listok.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");

        List<EducationExperience> listik = new ArrayList<EducationExperience>();
        listik.add(new EducationExperience("petrovich", "petronov", LocalDate.of(2012, Month.APRIL, 5), LocalDate.of(2016, Month.AUGUST, 10), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike"));

        List<EducationExperience> listikon = new ArrayList<EducationExperience>();
        listikon.add(new EducationExperience("petrovich", "petronov", LocalDate.of(2006, Month.APRIL, 5), LocalDate.of(2011, Month.AUGUST, 10), "Студент", "Инженер"));

        Resume resume = new Resume("Ivan", "Petrov");

        resume.setContacts(ContactsType.Phone, "7989565664");
        resume.setContacts(ContactsType.Mail, "petrov@mail.ru");
        resume.setContacts(ContactsType.Homepage, "www.petrov.ru");
        resume.setContacts(ContactsType.Skype, "i.petrov");
        resume.setContacts(ContactsType.GitHub, "petrovich");
        resume.setContacts(ContactsType.LinkedIn, "petrusha");
        resume.setContacts(ContactsType.Stackoverflow, "petronav");
        resume.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий разработчик"));
        resume.setSections(SectionType.PERSONAL, new TextSection("Ответственный, трудолюбивый,исполнительный."));
        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(list));
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection(listok));
        resume.setSections(SectionType.EXPERIENCE, new EducationExpirienceSection(listik));
        resume.setSections(SectionType.EDUCATION, new EducationExpirienceSection(listikon));

        System.out.println(resume.toString());
        System.out.println();
        for (ContactsType type : ContactsType.values()) {
            System.out.println(type.getTitle());
            System.out.println(resume.getContacts(type));
            System.out.println();
        }
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
            System.out.println(resume.getSections(type));
            System.out.println();
        }

    }
}
