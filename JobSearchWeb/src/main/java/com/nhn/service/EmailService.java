package com.nhn.service;

import com.nhn.model.request.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    Boolean sendSimpleMail(EmailDetails details);

}
