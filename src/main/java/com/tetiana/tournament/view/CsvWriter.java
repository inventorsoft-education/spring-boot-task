package com.tetiana.tournament.view;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.tetiana.tournament.domain.Team;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvWriter {
  public static void write(List<Team> list, String pathTeam) {
    try (Writer writer = new FileWriter(pathTeam)) {
      StatefulBeanToCsv<Team> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Team>(writer).build();
      statefulBeanToCsv.write(list);
    }
    catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
      e.printStackTrace();
    }
  }
}
