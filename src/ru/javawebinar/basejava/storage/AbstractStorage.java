package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object masterKey = getIfNotExist(resume.getUuid());
        isUpdate(resume, masterKey);
    }

    public void save(Resume resume) {
        Object masterKey = getIfExist(resume.getUuid());
        isSave(resume, masterKey);
    }

    public void delete(String uuid) {
        Object masterKey = getIfNotExist(uuid);
        isDelete(masterKey);
    }

    public Resume get(String uuid) {
        Object masterKey = getIfNotExist(uuid);
        return isGet(masterKey);
    }

    private Object getIfExist(String uuid) {
        Object masterKey = getIndex(uuid);
        if (isExist(masterKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return masterKey;
        }
    }

    private Object getIfNotExist(String uuid) {
        Object masterKey = getIndex(uuid);
        if (!isExist(masterKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return masterKey;
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void isUpdate(Resume resume, Object masterKey);

    protected abstract void isSave(Resume resume, Object masterKey);

    protected abstract void isDelete(Object masterKey);

    protected abstract Resume isGet(Object masterKey);

    protected abstract boolean isExist(Object masterKey);


}
