package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactsType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactsType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                if (type == SectionType.OBJECTIVE || type == SectionType.PERSONAL) {
                    dos.writeUTF(((TextSection) section).getFilling());
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    int size = ((ListSection) section).getFilling().size();
                    dos.writeInt(size);
                    for (int i = 0; i < size; i++) {
                        dos.writeUTF(((ListSection) section).getFilling().get(i));
                    }
                } else if (type == SectionType.EDUCATION || type == SectionType.EXPERIENCE) {
                    int size = ((EducationExpirienceSection) section).getEducationExperiences().size();
                    dos.writeInt(size);
                    List<EducationExperience> list = ((EducationExpirienceSection) section).getEducationExperiences();
                    for (int i = 0; i < size; i++) {
                        dos.writeUTF(list.get(i).getPage().getName());
                        dos.writeUTF(list.get(i).getPage().getUrl());
                        int sizeAction = list.get(i).getActions().size();
                        dos.writeInt(sizeAction);
                        for (int j = 0; j < sizeAction; j++) {
                            writeLocalDate(dos, list.get(i).getActions().get(j).getStartDate());
                            writeLocalDate(dos, list.get(i).getActions().get(j).getEndDate());
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
            for (int i = 0; i < size; i++) {
                resume.setContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                if (type == SectionType.OBJECTIVE || type == SectionType.PERSONAL) {
                    resume.setSections(type, new TextSection(dis.readUTF()));
                } else if (type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATIONS) {
                    int sizeList = dis.readInt();
                    List<String> list = new ArrayList<>(sizeList);
                    for (int j = 0; j < sizeList; j++) {
                        list.add(j, dis.readUTF());
                    }
                    resume.setSections(type, new ListSection(list));
                } else if (type == SectionType.EDUCATION || type == SectionType.EXPERIENCE) {
                    int sizeList = dis.readInt();
                    List<EducationExperience> list = new ArrayList<>(sizeList);
                    for (int j = 0; j < sizeList; j++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        int sizeAction = dis.readInt();
                        List<Action> listAction = new ArrayList<>(sizeAction);
                        for (int k = 0; k < sizeAction; k++) {
                            listAction.add(k, new Action(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()));
                        }
                        list.add(new EducationExperience(name, url, listAction));
                    }
                    resume.setSections(type, new EducationExpirienceSection(list));
                }
            }
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
}
