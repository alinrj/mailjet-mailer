package com.mailer.mailermailjet.controller;

import com.mailer.mailermailjet.dto.EmailDataDto;
import com.mailer.mailermailjet.service.MailjetService;
import com.mailjet.client.errors.MailjetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mailjet")
public class MailjetController {

    @Autowired
    private MailjetService mailjetService;

    @PostMapping("/send-mail")
    public void sayHello(@RequestBody EmailDataDto emailData) throws MailjetException {
        mailjetService.sendEmail(emailData);
    }
}
