package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    public Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume resume_1;
    private static final Resume resume_2;
    private static final Resume resume_3;
    private static final Resume resume_4;

    static {
        resume_1 = new Resume(UUID_1, "name1");
        resume_2 = new Resume(UUID_2, "name2");
        resume_3 = new Resume(UUID_3, "name3");
        resume_4 = new Resume(UUID_4, "name4");

        /*List<Action> workOrganization = new ArrayList<>();
        workOrganization.add(new Action(LocalDate.of(2014, 5, 1), LocalDate.of(2013, 4, 1), "Разработчик", "Разработка ПО"));
        workOrganization.add(new Action(LocalDate.of(2016, 5, 1), LocalDate.of(2017, 8, 1), "Тестировщик", "Тестирование ПО"));
        List<EducationExperience> expirience = new ArrayList<>();
        expirience.add(new EducationExperience("Organization1", "Organization1.ru", workOrganization));

        List<Action> educationalOrganization = new ArrayList<>();
        educationalOrganization.add(new Action(LocalDate.of(2012, 5, 1), LocalDate.of(2013, 4, 1), "Студент", "Изучение основ программирования"));
        List<EducationExperience> educational = new ArrayList<>();
        educational.add(new EducationExperience("Organization2", "Organization2.ru", educationalOrganization));
        resume_1.setContacts(ContactsType.Phone, "7989565664");
        resume_1.setContacts(ContactsType.Mail, "petrov@mail.ru");
        resume_1.setContacts(ContactsType.Homepage, "www.petrov.ru");
        resume_1.setContacts(ContactsType.Skype, "i.petrov");
        resume_1.setContacts(ContactsType.GitHub, "petrovich");
        resume_1.setContacts(ContactsType.LinkedIn, "petrusha");
        resume_1.setContacts(ContactsType.Stackoverflow, "petronav");
        resume_1.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий разработчик"));
        resume_1.setSections(SectionType.PERSONAL, new TextSection("Ответственный, трудолюбивый,исполнительный."));
        resume_1.setSections(SectionType.EXPERIENCE, new EducationExpirienceSection(expirience));
        resume_1.setSections(SectionType.EDUCATION, new EducationExpirienceSection(educational));
        resume_2.setContacts(ContactsType.Skype, "rrtryy");
        resume_2.setContacts(ContactsType.Phone, "5465466567");
        resume_4.setContacts(ContactsType.Homepage, "vasechkin.com");
        resume_4.setContacts(ContactsType.Mail , "vasechkin@mail.com");
*/
    }


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        newResume.setContacts(ContactsType.Mail, "petrov.com");
        newResume.setContacts(ContactsType.Skype, "petrovich");
        newResume.setContacts(ContactsType.Phone, "8951325687");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(resume_1, resume_2, resume_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        assertGet(resume_1);
        assertGet(resume_2);
        assertGet(resume_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void save() {
        storage.save(resume_4);
        assertEquals(4, storage.size());
        assertEquals(resume_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume_1);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}