package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object index = getIfNotExist(resume.getUuid());
        isUpdate(resume, index);
    }

    public void save(Resume resume) {
        Object index = getIfExist(resume.getUuid());
        isSave(resume, index);
    }

    public void delete(String uuid) {
        Object index = getIfNotExist(uuid);
        isDelete(index);
    }

    public Resume get(String uuid) {
        Object index = getIfNotExist(uuid);
        return isGet(index);
    }

    private Object getIfExist(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object getIfNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void isUpdate(Resume resume, Object index);

    protected abstract void isSave(Resume resume, Object index);

    protected abstract void isDelete(Object index);

    protected abstract Resume isGet(Object index);

    protected abstract boolean isExist(Object index);


}
