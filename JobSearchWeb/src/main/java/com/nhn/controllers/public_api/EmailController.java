package com.nhn.controllers.public_api;

import com.nhn.dto.request.EmailDetails;
import com.nhn.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public boolean sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

}
