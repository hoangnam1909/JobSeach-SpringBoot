package com.nhn.service;

import com.nhn.dto.request.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    Boolean sendSimpleMail(EmailDetails details);

}
