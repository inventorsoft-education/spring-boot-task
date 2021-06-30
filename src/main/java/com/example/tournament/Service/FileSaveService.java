package com.example.tournament.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileSaveService {

    public void saveToFile(String fileName, String stringObject) throws IOException {
        File f1 = new File("D:\\InventorSovtTasks\\TournaventWithSpring\\myObjects.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }

        FileWriter fileWritter = new FileWriter(f1.getName(), true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        bw.write(stringObject);
        bw.close();

    }
}
