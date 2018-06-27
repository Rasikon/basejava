package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    protected HashMap<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected void izSave(Resume resume, int index) {

    }

    @Override
    protected void izDelete(int index) {

    }

    @Override
    protected Resume izGet(int index) {
        return null;
    }

    @Override
    protected void izUpdate(Resume resume, int index) {

    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

}
