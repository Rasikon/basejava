package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object index = getIfNotExist(resume.getUuid());
        izUpdate(resume, index);
    }

    public void save(Resume resume) {
        Object index = getIfExist(resume.getUuid());
        izSave(resume, index);
    }

    public void delete(String uuid) {
        Object index = getIfNotExist(uuid);
        izDelete(index);
    }

    public Resume get(String uuid) {
        Object index = getIfNotExist(uuid);
        return izGet(index);
    }

    private Object getIfExist(String uuid) {
        Object index = getIndex(uuid);
        if (izExist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object getIfNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (!izExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void izUpdate(Resume resume, Object index);

    protected abstract void izSave(Resume resume, Object index);

    protected abstract void izDelete(Object index);

    protected abstract Resume izGet(Object index);

    protected abstract boolean izExist(Object index);


}
