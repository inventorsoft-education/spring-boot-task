package com.sender.email;

import com.sender.email.repos.EmailProcessing;
import com.sender.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/emails")
@AllArgsConstructor
public class EmailController {
    EmailProcessing processing;
    EmailService emailService;

    @GetMapping
    public List<Email> getEmails() {
        return processing.getAll();
    }

    @PostMapping(value = "/new", consumes = "application/json")
    public void newEmail(@RequestBody List<Email> email){
        processing.addNewEmail(email);
        emailService.sendAll();
    }

    @PutMapping(value = "/change-date", produces = "application/json")
    public @ResponseBody Email updateDate(@RequestParam(value="id", required=true) int id,@RequestParam(value="date", required=true) @DateTimeFormat(pattern = "dd-mm-yyyy HH:mm") Date newDate){
        processing.changeDate(id, newDate);
        return processing.getAll().get(id);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteEmail(@RequestParam(value="id", required=true) int id, HttpServletResponse response) {
        if (id >= processing.getAll().size()) {
            response.setHeader("Location", "/error");
            return ResponseEntity.status(404).body("404\nIndex out of range");
        }

        processing.delete(id);
        return ResponseEntity.ok(String.format("Email with id: %s has been deleted!", id));
    }

}
