package com.home_work.spring_boot.repository;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MailDao {

    private PropertiesService propertiesService;

    @Autowired
    public MailDao(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    public void saveMail(Letter letter) {
        List<Letter> letters = getMails();
        giveId(letter, letters);
        letters.add(letter);
        try (OutputStream outputStream = new FileOutputStream(getPathAndNameOfFile());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(letters);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void giveId(Letter letter, List<Letter> letters) {
        final long ID_INCREMENT = 1;
        final long DEFAULT_ID = 0;
        if (letters.isEmpty()) {
            letter.setId(DEFAULT_ID);
        } else {
            letter.setId(letters.get(letters.size() - 1).getId() + ID_INCREMENT);
        }
    }

    public List<Letter> getMails() {

        List<Letter> letters = null;
        try (InputStream inputStream = new FileInputStream(isFileExist());
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            letters = (List<Letter>) objectInputStream.readObject();

        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return letters;
    }

    private File isFileExist() {
        File file = new File(getPathAndNameOfFile());
        try {
            if (file.createNewFile())
                return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private String getPathAndNameOfFile() {
        Map<String, String> fileProperties = propertiesService.getFileProperties();
        StringBuilder pathAndNameOfFile = new StringBuilder(fileProperties.get("path"));
        pathAndNameOfFile.append(File.separator);
        pathAndNameOfFile.append(fileProperties.get("fileName"));
        return pathAndNameOfFile.toString();
    }
}
