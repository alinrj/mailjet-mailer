package com.mailer.mailermailjet.controller;

import com.mailer.mailermailjet.dto.EmailDataDto;
import com.mailer.mailermailjet.service.MailjetService;
import com.mailjet.client.errors.MailjetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mailjet")
public class MailjetController {

    @Autowired
    private MailjetService mailjetService;

    @GetMapping("/keep-alive")
    public ResponseEntity<String> keepAlive() {
        System.out.println("[API_CALL] Keep-alive method called: I am still alive :)");

        return new ResponseEntity<>("The system is alive :)", HttpStatus.OK);
    }

    @PostMapping("/send-mail")
    public void sayHello(@RequestBody EmailDataDto emailData) throws MailjetException {
        mailjetService.sendEmail(emailData);
    }
}
