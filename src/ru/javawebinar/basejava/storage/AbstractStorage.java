package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<K> implements Storage {

    public void update(Resume resume) {
        K key = getIfNotExist(resume.getUuid());
        doUpdate(key, resume);
    }

    public void save(Resume resume) {
        K key = getIfExist(resume.getUuid());
        doSave(key, resume);
    }

    public void delete(String uuid) {
        K key = getIfNotExist(uuid);
        doDelete(key);
    }

    public Resume get(String uuid) {
        K key = getIfNotExist(uuid);
        return doGet(key);
    }

    private K getIfExist(String uuid) {
        K key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    private K getIfNotExist(String uuid) {
        K key = getKey(uuid);
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

    protected abstract K getKey(String uuid);

    protected abstract void doUpdate(K key, Resume resume);

    protected abstract void doSave(K key, Resume resume);

    protected abstract void doDelete(K key);

    protected abstract Resume doGet(K key);

    protected abstract boolean isExist(K key);

    protected abstract List<Resume> getCopy();
}