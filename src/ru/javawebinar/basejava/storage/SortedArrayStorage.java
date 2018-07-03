package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {


    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void remove(int index) {
        int shift = size - index - 1;
        if (shift > 0) {
            System.arraycopy(storage, index + 1, storage, index, shift);
        }
    }

    @Override
    protected void paste(Resume resume, int index) {
        int shift = -index - 1;
        System.arraycopy(storage, shift, storage, shift + 1, size - shift);
        storage[shift] = resume;
    }

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "name");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
