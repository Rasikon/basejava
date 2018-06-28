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
    protected void izSave(Resume resume, Object index) {
        list.add(resume);
    }

    @Override
    protected void izDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume izGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void izUpdate(Resume resume, Object index) {
        list.set((Integer) index, resume);
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
    protected boolean izExist(Object index) {
        return index != null;
    }
}



