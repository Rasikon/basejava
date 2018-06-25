package ru.javawebinar.basejava;

import org.omg.CORBA.Object;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume resume_1 = new Resume(UUID_1);
    private static final Resume resume_2 = new Resume(UUID_2);
    private static final Resume resume_3 = new Resume(UUID_3);
    private static final Resume resume_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(resume_1);
        collection.add(resume_2);
        collection.add(resume_3);

        for (Resume r : collection) {
            System.out.println(r);
            if(r.getUuid().equals(UUID_1)){
//                collection.remove(r);
            }
        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()){
            Resume r = iterator.next();
            System.out.println(r);
            if(r.getUuid().equals(UUID_1)){
               iterator.remove();
            }

        }


        System.out.println(collection.toString());


    }
}
