package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serializer serializer;

    protected PathStorage(String dir, Serializer serializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        this.serializer = serializer;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not readable/writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    protected List<Resume> getCopy() {
        try {
            return Files.list(directory).map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory error", null, e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory error", null, e);
        }
    }

    @Override
    protected void doSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete error", path.getFileName().toString(), e);
        }

    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            serializer.doWrite(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException("Write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Path getKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

}
