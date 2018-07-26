package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public void searchFile(File dir, String space) throws IOException {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(space + "File " + file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println(space + "Catalog " + file.getName());
                        searchFile(file, space + " ");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File dir = new File("D:\\basejava\\src\\ru");
        MainFile file = new MainFile();
        file.searchFile(dir, "");

    }
}




