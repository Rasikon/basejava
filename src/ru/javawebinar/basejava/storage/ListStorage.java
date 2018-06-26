package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> list = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (Resume resume : list) {
            if (uuid.equals(resume.getUuid())) {
                return list.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void izUpdate(Resume resume, int index) {
        list.set(index, resume);
    }

    @Override
    public void izSave(Resume resume, int index) {
        list.add(resume);
    }

    @Override
    public Resume izGet(int index) {
        return list.get(index);
    }

    @Override
    public void izDelete(int index) {
        list.remove(index);
    }

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


}



