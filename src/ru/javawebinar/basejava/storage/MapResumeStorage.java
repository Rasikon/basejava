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
    protected Object getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        map.remove(((Resume) key).getUuid());
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

}
