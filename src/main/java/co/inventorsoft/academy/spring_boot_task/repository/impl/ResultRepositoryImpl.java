package co.inventorsoft.academy.spring_boot_task.repository.impl;

import co.inventorsoft.academy.spring_boot_task.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Repository
public class ResultRepositoryImpl implements ResultRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(ResultRepositoryImpl.class);
    @Value("${tournament.results}")
    private String results;

    @Override
    public void writeResultToFile(List<String> list) {
        try (FileWriter csvWriter = new FileWriter(results, true)) {
            File file = new File(results);
            if (file.length() == 0) {
                csvWriter.append("Round");
                csvWriter.append(", ");
                csvWriter.append("Team 1");
                csvWriter.append(", ");
                csvWriter.append("Team 2");
                csvWriter.append(", ");
                csvWriter.append("Score");
                csvWriter.append("\n");
            }
            csvWriter.append(String.join(", ", list));
            csvWriter.append("\n");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
