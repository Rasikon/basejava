package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
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
    protected void doUpdate(String key, Resume resume) {
        map.put(key, resume);
    }

    @Override
    protected void doSave(String key, Resume resume) {
        map.put(key, resume);
    }

    @Override
    protected void doDelete(String key) {
        map.remove(key);
    }

    @Override
    protected Resume doGet(String key) {
        return map.get(key);
    }

    @Override
    protected boolean isExist(String key) {
        return map.containsKey(key);
    }
}
