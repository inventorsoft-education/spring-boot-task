package com.paskar.email.application.repositiory;


import com.paskar.email.application.console.Email;

import java.io.IOException;
import java.util.List;

public interface CreateAndSaveEmail {
   void save(List<Email> email) throws IOException;
    Email createNewEmail() throws IOException;
}
