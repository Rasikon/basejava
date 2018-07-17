package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filepath = "D:\\basejava\\.gitignore";
        File file = new File("D:\\basejava\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("D:\\basejava\\src\\ru\\javawebinar\\basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }


        try (FileInputStream fis = new FileInputStream(filepath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




