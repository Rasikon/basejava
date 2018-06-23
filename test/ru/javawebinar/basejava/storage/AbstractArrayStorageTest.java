package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume resume_1 = new Resume(UUID_1);
    private static final Resume resume_2 = new Resume(UUID_2);
    private static final Resume resume_3 = new Resume(UUID_3);
    private static final Resume resume_4 = new Resume(UUID_4);


    public AbstractArrayStorageTest(Storage storage) {
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
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume_1);
        Assert.assertEquals(resume_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume_4);
    }

    @Test
    public void getAll() {
        Resume[] arr = {resume_1,resume_2,resume_3};
        Assert.assertArrayEquals(arr,storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, storage.get(UUID_1).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void save() {
        storage.save(resume_4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            int i = storage.size();
            while (i < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
                i++;
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());

    }
}