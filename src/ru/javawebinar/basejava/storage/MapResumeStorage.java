package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
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
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume key, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume key) {
        map.remove((key).getUuid());
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

}
