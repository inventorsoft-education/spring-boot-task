package main;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

@Component
public class FileRW {

    public FileRW() {
    }
    public LinkedList<Team> readTeamsFromFile(String path) {
        LinkedList<Team> teams = new LinkedList<>();
        try (FileReader fileReader = new FileReader(path)) {
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr : strings) {
                teams.add(new Team(arr[2], arr[0], arr[1]));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return teams;
    }
    public void writeMatch(String racePath, List<Race> raceList) {
        try (Writer writer = new FileWriter(racePath)) {
            StatefulBeanToCsv statefulBeanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            statefulBeanToCsv.write(raceList);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
