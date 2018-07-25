package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(OutputStream os, Resume resume) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not readable/writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }
}
/*
    @Override
    protected List<Resume> getCopy() {
        Path[] listPaths = directory.listPaths();
        if (listPaths == null) {
            throw new StorageException("Directory error", null);
        }
        List<Resume> list = new ArrayList<>(listPaths.length);
        for (Path Path : listPaths) {
            list.add(doGet(Path));
        }
        return list;
    }

    @Override
    public int size() {
        Path[] listPaths = directory.listPaths();
        if (listPaths == null) {
            throw new StorageException("Directory error", null);
        }
        return listPaths.length;
    }

    @Override
    protected void doSave(Path Path, Resume resume) {
        try {
            Path.createNewPath();
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
        doUpdate(Path, resume);
    }

    @Override
    protected void doDelete(Path Path) {
        if (!Path.delete()) {
            throw new StorageException("Delete error", Path.getName());
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Read error", Path.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Path Path, Resume resume) {
        try {
            doWrite(new BufferedOutputStream(new PathOutputStream(Path)), resume);
        } catch (IOException e) {
            throw new StorageException("Write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Path getKey(String uuid) {
        return new Path(directory, uuid);
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

}*/
