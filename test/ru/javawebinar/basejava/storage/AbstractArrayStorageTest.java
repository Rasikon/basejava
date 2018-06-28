package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
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
            Assert.fail("Overflow");
        }
        storage.save(new Resume());
    }
}