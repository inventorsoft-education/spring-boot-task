package co.inventorsoft.springmail.dao;

import co.inventorsoft.springmail.model.Email;

import java.io.IOException;
import java.util.List;

public interface CrudDao<T> {


    List<Email> findAll() throws IOException;

    void replaceAll(List<Email> emails);

    void saveMail(Email email) throws IOException;

    void saveAll(List<Email> emails) throws IOException;

    void delete(Email email) throws IOException;

    void deleteAll(List<Email> emails) throws IOException;
}
