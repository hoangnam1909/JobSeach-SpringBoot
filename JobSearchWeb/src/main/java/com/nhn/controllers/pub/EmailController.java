package com.nhn.controllers.pub;

import com.nhn.model.request.EmailDetails;
import com.nhn.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public boolean sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

}