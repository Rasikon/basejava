package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected void isSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        paste(resume, (Integer) index);
        size++;
    }

    @Override
    protected void isDelete(Object index) {
        remove((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume isGet(Object index) {
        return storage[(Integer) index];
    }


    @Override
    protected void isUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void remove(int index);

    protected abstract void paste(Resume resume, int index);
}
