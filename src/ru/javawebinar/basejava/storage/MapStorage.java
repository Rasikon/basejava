package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> map = new HashMap<>();

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
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void isUpdate(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    protected void isSave(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    protected void isDelete(Object index) {
        map.remove((String) index);
    }

    @Override
    protected Resume isGet(Object index) {
        return map.get((String) index);
    }

    @Override
    protected boolean isExist(Object index) {
        return map.containsKey((String) index);
    }
}
