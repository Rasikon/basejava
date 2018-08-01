package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.ContactsType;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements Serializer{

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactsType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for(Map.Entry<ContactsType,String> entry: contacts.entrySet()){
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for(int i =0; i<size;i++) {
                resume.setContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            }
            return resume;
        }
    }
}
