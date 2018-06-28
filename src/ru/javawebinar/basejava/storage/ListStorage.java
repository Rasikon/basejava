package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void isSave(Resume resume, Object masterKey) {
        list.add(resume);
    }

    @Override
    protected void isDelete(Object masterKey) {
        list.remove(((Integer) masterKey).intValue());
    }

    @Override
    protected Resume isGet(Object masterKey) {
        return list.get((Integer) masterKey);
    }

    @Override
    protected void isUpdate(Resume resume, Object masterKey) {
        list.set((Integer) masterKey, resume);
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object masterKey) {
        return masterKey != null;
    }
}



