package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactsType, String> contacts = resume.getContacts();
            writeCollection (dos, contacts.entrySet(),entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection (dos, resume.getSections().entrySet(),entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                if (type == SectionType.OBJECTIVE || type == SectionType.PERSONAL) {
                    dos.writeUTF(((TextSection) section).getFilling());
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    writeCollection (dos,((ListSection) section).getFilling(),dos::writeUTF);
                } else if (type == SectionType.EDUCATION || type == SectionType.EXPERIENCE) {

                    writeCollection(dos, ((EducationExpirienceSection) section).getEducationExperiences(),  organization-> {
                        dos.writeUTF(organization.getPage().getName());
                        dos.writeUTF(organization.getPage().getUrl());
                        writeCollection (dos,organization.getActions(),action ->  {
                            writeLocalDate(dos, action.getStartDate());
                            writeLocalDate(dos, action.getEndDate());
                            dos.writeUTF(action.getTitle());
                            dos.writeUTF(action.getContent());
                        });
                    });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.setContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> { SectionType type = SectionType.valueOf(dis.readUTF());
                if (type == SectionType.OBJECTIVE || type == SectionType.PERSONAL) {
                    resume.setSections(type, new TextSection(dis.readUTF()));
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    resume.setSections(type, new ListSection(readList(dis, dis::readUTF)));
                } else if (type == SectionType.EDUCATION || type == SectionType.EXPERIENCE) {
                    resume.setSections(type, new EducationExpirienceSection(
                            readList(dis, ()->new EducationExperience(dis.readUTF(), dis.readUTF(),
                                    readList(dis, ()-> new Action(
                                            readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                                    ))
                            ))));
                }
            });
            return resume;
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private interface WriterItems<T>{
        void write(T t) throws IOException;
    }

    private interface ReaderItems{
        void read() throws IOException;
    }

    private interface ReaderList<T> {
        T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, WriterItems<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dis, ReaderItems reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ReaderList<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
