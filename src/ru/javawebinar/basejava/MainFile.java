package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public void searchFile(File dir) throws IOException {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    } else if (file.isDirectory()) {
                        searchFile(file);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
 /*       String filepath = "D:\\basejava\\.gitignore";
        File file = new File("D:\\basejava\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }*/
        File dir = new File("D:\\basejava\\");
        MainFile file = new MainFile();
        file.searchFile(dir);


/*        try (FileInputStream fis = new FileInputStream(filepath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}




