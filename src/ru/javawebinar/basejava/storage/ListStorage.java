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
    protected List<Resume> getCopy() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Object key) {
        list.remove(((Integer) key).intValue());
    }

    @Override
    protected Resume doGet(Object key) {
        return list.get((Integer) key);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        list.set((Integer) key, resume);
    }

    @Override
    protected Integer getKey(String uuid) {
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



