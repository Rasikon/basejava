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
    protected Object getIndex(String uuid) {
        return null;
    }

    @Override
    protected void izUpdate(Resume resume, Object index) {

    }

    @Override
    protected void izSave(Resume resume, Object index) {

    }

    @Override
    protected void izDelete(Object index) {

    }

    @Override
    protected Resume izGet(Object index) {
        return null;
    }

    @Override
    protected boolean izExist(Object index) {
        return false;
    }
}
