package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    Resume resume_1 = new Resume(UUID_1);
    Resume resume_2 = new Resume(UUID_2);
    Resume resume_3 = new Resume(UUID_3);
    Resume resume_4 = new Resume(UUID_4);


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
        Assert.assertTrue(resume_1 == storage.get(UUID_1));

    }

    @Test
    public void getAll() {
        Resume[] arr = storage.getAll();
        Assert.assertEquals(3, arr.length);
    }

    @Test
    public void save() {
        storage.save(resume_4);
        Assert.assertEquals(4, storage.size());
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test
    public void get() {
        Assert.assertTrue(storage.get(UUID_1).getUuid().equals("uuid1"));

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getExist() {
        Assert.assertTrue(UUID_1.equals(storage.get(UUID_1).getUuid()));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            int i = storage.size();
            while (i <= AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }

    }
}