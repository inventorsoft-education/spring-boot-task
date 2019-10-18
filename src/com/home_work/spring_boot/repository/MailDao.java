package com.home_work.spring_boot.repository;

import com.home_work.spring_boot.entity.Letter;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class MailDao {

    private static final String FILE_NAME = "mails";

    public void saveMail(Letter letter) throws IOException {
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(FILE_NAME);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(letter);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
            } catch (Exception ex) {

            }
        }
    }

    public Letter getLetter() {
        InputStream inputStream;
        ObjectInputStream objectInputStream = null;
        Letter letter = null;
        try {
            inputStream = new FileInputStream(FILE_NAME);
            objectInputStream = new ObjectInputStream(inputStream);
            letter = (Letter) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) objectInputStream.close();
            } catch (Exception ex) {

            }
        }
        return letter;
    }
}
