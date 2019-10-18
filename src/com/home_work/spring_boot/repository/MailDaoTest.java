package com.home_work.spring_boot.repository;

import com.home_work.spring_boot.entity.Letter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class MailDaoTest {


    @Test
    public void saveMail() {
        Letter letter = new Letter();
        letter.setRecipient("aswdwadadawdwad");
        letter.setSubject("awdadawdawdawd");
        letter.setBody("awdadwadafwf");
        letter.setDeliveryTime(LocalDateTime.now());
        MailDao mailDao = new MailDao();
        Integer i = 1;
        Integer w = 2;
        System.out.println(i.compareTo(w));
        try {
            mailDao.saveMail(letter);
            assertEquals(mailDao.getLetter(),letter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}