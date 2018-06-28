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
    protected void isUpdate(Object masterKey, Resume resume) {
        map.put((String) masterKey, resume);
    }

    @Override
    protected void isSave(Object masterKey, Resume resume) {
        map.put((String) masterKey, resume);
    }

    @Override
    protected void isDelete(Object masterKey) {
        map.remove((String) masterKey);
    }

    @Override
    protected Resume isGet(Object masterKey) {
        return map.get((String) masterKey);
    }

    @Override
    protected boolean isExist(Object masterKey) {
        return map.containsKey((String) masterKey);
    }
}
