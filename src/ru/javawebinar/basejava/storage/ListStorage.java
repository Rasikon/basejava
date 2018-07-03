package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected List<Resume> getCopy() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void isSave(Object masterKey, Resume resume) {
        list.add(resume);
    }

    @Override
    protected void isDelete(Object masterKey) {
        list.remove(((Integer) masterKey).intValue());
    }

    @Override
    protected Resume isGet(Object masterKey) {
        return list.get((Integer) masterKey);
    }

    @Override
    protected void isUpdate(Object masterKey, Resume resume) {
        list.set((Integer) masterKey, resume);
    }

    @Override
    protected Integer getMasterKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object masterKey) {
        return masterKey != null;
    }

}



