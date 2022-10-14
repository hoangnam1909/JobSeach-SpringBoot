package com.nhn.controllers.pub;

import com.nhn.model.request.EmailDetails;
import com.nhn.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-mail")
    public boolean sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

}
