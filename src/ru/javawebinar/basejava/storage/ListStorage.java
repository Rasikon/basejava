package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> list = new ArrayList<>();
    protected int size;

    @Override
    protected int getIndex(String uuid) {
        for(Resume resume:list) {
            if (uuid.equals(resume.getUuid())){
                return list.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void renew(Resume resume, int index) {
        list.set(index,resume);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return list.size();
    }


}



