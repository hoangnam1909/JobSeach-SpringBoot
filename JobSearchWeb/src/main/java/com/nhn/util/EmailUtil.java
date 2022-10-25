package com.nhn.util;

import com.nhn.entity.User;
import com.nhn.model.request.EmailDetails;

public class EmailUtil {

    public static EmailDetails otpMailForm(User user, String verificationCode){
        EmailDetails emailDetails = new EmailDetails();

        String subject = "Mã xác thực: [[code]]";

        String body = "<h2>Xin chào [[name]]</h2>"
                + "<h2><b>Your verification code</b></h2>"
                + "<h1>[[code]]</h1>"
                + "Thank you,<br>"
                + "Job Search Spring Boot";

        body = body.replace("[[name]]", user.getFullName());
        body = body.replace("[[code]]", verificationCode);
        subject = subject.replace("[[code]]", verificationCode);

        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(body);

        emailDetails.setRecipient(user.getEmail());
        return emailDetails;
    }

}
