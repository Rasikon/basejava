package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void doSave(Integer key, Resume resume) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Integer key) {
        list.remove(key.intValue());
    }

    @Override
    protected Resume doGet(Integer key) {
        return list.get(key);
    }

    @Override
    protected void doUpdate(Integer key, Resume resume) {
        list.set(key, resume);
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
    protected boolean isExist(Integer masterKey) {
        return masterKey != null;
    }

}



