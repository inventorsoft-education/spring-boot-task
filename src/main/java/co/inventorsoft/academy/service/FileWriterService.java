package co.inventorsoft.academy.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
@Service
@NoArgsConstructor
public class FileWriterService {
    /**
     * This method write to file String value of str parameter
     * @param str String value
     */
    public void write(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("data.csv", true));
            writer.write(str + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
