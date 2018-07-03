package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object key = getIfNotExist(resume.getUuid());
        doUpdate(key, resume);
    }

    public void save(Resume resume) {
        Object key = getIfExist(resume.getUuid());
        doSave(key, resume);
    }

    public void delete(String uuid) {
        Object key = getIfNotExist(uuid);
        doDelete(key);
    }

    public Resume get(String uuid) {
        Object key = getIfNotExist(uuid);
        return doGet(key);
    }

    private Object getIfExist(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    private Object getIfNotExist(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getCopy();
        Collections.sort(list);
        return list;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void doUpdate(Object key, Resume resume);

    protected abstract void doSave(Object key, Resume resume);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

    protected abstract boolean isExist(Object key);

    protected abstract List<Resume> getCopy();
}