package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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
    protected Object getMasterKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void isUpdate(Object masterKey, Resume resume) {
        map.put(((Resume)masterKey).getUuid(),resume);
    }

    @Override
    protected void isSave(Object masterKey, Resume resume) {
        map.put(((Resume)masterKey).getUuid(),resume);
    }

    @Override
    protected void isDelete(Object masterKey) {
        map.remove(((Resume)masterKey).getUuid());
    }

    @Override
    protected Resume isGet(Object masterKey) {
        return (Resume) masterKey;
    }

    @Override
    protected boolean isExist(Object masterKey) {
        return map.containsKey(((Resume) masterKey).getUuid());
    }

}
