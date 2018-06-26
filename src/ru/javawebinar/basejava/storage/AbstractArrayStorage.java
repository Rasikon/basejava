package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void izSave(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        paste(resume, index);
        size++;
    }

    @Override
    public void izDelete(int index) {
        remove(index);
        storage[size - 1] = null;
        size--;
    }
    
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    public Resume izGet(int index) {
        return storage[index];
    }


    @Override
    protected void izUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void remove(int index);

    protected abstract void paste(Resume resume, int index);
}
