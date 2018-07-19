package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new StorageException("Directory error", null);
        }
        for (File file : listFiles) {
            doDelete(file);
        }
    }

    @Override
    protected List<Resume> getCopy() {
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new StorageException("Directory error", null);
        }
        List<Resume> list = new ArrayList<>(listFiles.length);
        for (File file : listFiles) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public int size() {
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new StorageException("Directory error", null);
        }
        return listFiles.length;
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(File file, Resume resume) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected void doDelete(File file) {

    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Read error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("Write error", resume.getUuid(), e);
        }
    }

    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

}
