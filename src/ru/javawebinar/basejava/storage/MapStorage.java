package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> getCopy() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        map.put((String) key, resume);
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        map.put((String) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        map.remove((String) key);
    }

    @Override
    protected Resume doGet(Object key) {
        return map.get((String) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return map.containsKey((String) key);
    }
}
