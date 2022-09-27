package Application.Managers;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterManager {

    /**
     * this method requires String. It will write this string into file Result.txt
     * @param s String
     */
    public static void writeSentance(String s) {
        try {
            FileWriter fw = new FileWriter("Result.txt", true);
            fw.write(s);
            fw.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
