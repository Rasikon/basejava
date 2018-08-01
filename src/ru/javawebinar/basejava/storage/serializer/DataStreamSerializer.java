package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.List;
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
            Map<SectionType,Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for(Map.Entry<SectionType,Section> entry:sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                if (type == SectionType.OBJECTIVE || type == SectionType.PERSONAL) {
                    dos.writeUTF(((TextSection) section).getFilling() );
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    int size = ((ListSection) section).getFilling().size();
                    for (int i = 0;i<size;i++) {
                        dos.writeUTF(((ListSection) section).getFilling().get(i));
                    }
                } else if (type == SectionType.EDUCATION || type == SectionType.EXPERIENCE) {
                    int size = ((EducationExpirienceSection) section).getEducationExperiences().size();
                    List<EducationExperience> list = ((EducationExpirienceSection) section).getEducationExperiences();
                    for (int i = 0;i<size;i++) {
                        dos.writeUTF(list.get(i).getPage().getName());
                        dos.writeUTF(list.get(i).getPage().getUrl());
                        for(int j=0;j<list.get(i).getActions().size();j++) {
                            dos.writeInt(list.get(i).getActions().get(j).getStartDate().getYear());
                            dos.writeInt(list.get(i).getActions().get(j).getStartDate().getMonth().getValue());
                            dos.writeInt(list.get(i).getActions().get(j).getEndDate().getYear());
                            dos.writeInt(list.get(i).getActions().get(j).getEndDate().getMonth().getValue());
                            dos.writeUTF(list.get(i).getActions().get(j).getTitle());
                            dos.writeUTF(list.get(i).getActions().get(j).getContent());
                        }
                    }
                }
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
