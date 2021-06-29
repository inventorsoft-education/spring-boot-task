package co.inventorsoft.academy.spring_boot_task.repository;

import java.util.List;

public interface ResultRepository {

    void writeResultToFile(List<String> list);
}
