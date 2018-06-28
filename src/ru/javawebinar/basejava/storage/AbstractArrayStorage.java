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
    protected void isSave(Object masterKey, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        paste(resume, (Integer) masterKey);
        size++;
    }

    @Override
    protected void isDelete(Object masterKey) {
        remove((Integer) masterKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume isGet(Object masterKey) {
        return storage[(Integer) masterKey];
    }


    @Override
    protected void isUpdate(Object masterKey, Resume resume) {
        storage[(Integer) masterKey] = resume;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void remove(int index);

    protected abstract void paste(Resume resume, int index);
}
