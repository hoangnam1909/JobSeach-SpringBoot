package com.nhn.util;

import com.nhn.entity.User;
import com.nhn.model.request.EmailDetails;

public class EmailUtil {

    public static EmailDetails oldOtpMailForm(User user, String verificationCode) {
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

    public static EmailDetails otpMailForm(User user, String verificationCode) {
        EmailDetails emailDetails = new EmailDetails();

        String subject = "Mã xác thực: [[code]]";

        String body = "<div\n" +
                "  style=\"display: flex; justify-content: center; margin: 0 auto; width: 550px\">\n" +
                "  <div\n" +
                "    style=\"\n" +
                "      border-style: solid;\n" +
                "      border-width: thin;\n" +
                "      border-color: #dadce0;\n" +
                "      border-radius: 8px;\n" +
                "      padding: 40px 20px;\n" +
                "    \"\n" +
                "    class=\"m_6229982311739101046mdv2rw\">\n" +
                "    <div style=\"display: flex; justify-content: center\">\n" +
                "      <img\n" +
                "        src=\"https://ci5.googleusercontent.com/proxy/T_zJ7UbaC9x27OP4-ZCPfDipqYLSGum30AlaxEycVclfvxO8Cze0sZ0kCrXlx6a-MgvW2tswbIyiNVfczjDuGh9okorzC5SUJDfwkHr6-3j1KUu94HuAw5uxM_jaElQef3Sub84=s0-d-e1-ft#https://www.gstatic.com/images/branding/googlelogo/2x/googlelogo_color_74x24dp.png\"\n" +
                "        height=\"35\"\n" +
                "        aria-hidden=\"true\"\n" +
                "        style=\"margin: 0 auto; margin-bottom: 16px\"\n" +
                "        alt=\"Google\"\n" +
                "        class=\"CToWUd\"\n" +
                "        data-bit=\"iit\" />\n" +
                "    </div>\n" +
                "    <div\n" +
                "      style=\"\n" +
                "        font-family: 'Google Sans', Roboto, RobotoDraft, Helvetica, Arial,\n" +
                "          sans-serif;\n" +
                "        border-bottom: thin solid #dadce0;\n" +
                "        color: rgba(0, 0, 0, 0.87);\n" +
                "        line-height: 32px;\n" +
                "        padding-bottom: 24px;\n" +
                "        text-align: center;\n" +
                "        word-break: break-word;\n" +
                "      \">\n" +
                "      <div style=\"font-size: 24px\">Verify your recovery email</div>\n" +
                "    </div>\n" +
                "    <div\n" +
                "      style=\"\n" +
                "        font-family: Roboto-Regular, Helvetica, Arial, sans-serif;\n" +
                "        font-size: 14px;\n" +
                "        color: rgba(0, 0, 0, 0.87);\n" +
                "        line-height: 20px;\n" +
                "        padding-top: 20px;\n" +
                "        text-align: left;\n" +
                "      \">\n" +
                "      We received a request to use\n" +
                "      <a style=\"font-weight: bold\">[[toEmail]]</a> as a recovery email for Job\n" +
                "      Search Spring Boot\n" +
                "      <a style=\"font-weight: bold\">dev.nhn1909@gmail.com</a>.<br /><br />Use\n" +
                "      this code to reset your password:<br />\n" +
                "      <div\n" +
                "        style=\"\n" +
                "          text-align: center;\n" +
                "          font-size: 36px;\n" +
                "          margin-top: 20px;\n" +
                "          line-height: 44px;\n" +
                "        \">\n" +
                "        [[code]]\n" +
                "      </div>\n" +
                "      <br />This code will expire in 3 minutes.<br /><br />If you don’t\n" +
                "      recognize <a style=\"font-weight: bold\">dev.nhn1909@gmail.com</a>, you can\n" +
                "      safely ignore this email.\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n";

        subject = subject.replace("[[code]]", verificationCode);

        body = body.replace("[[toEmail]]", user.getEmail());
        body = body.replace("[[code]]", verificationCode);

        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(body);

        emailDetails.setRecipient(user.getEmail());
        return emailDetails;
    }

}
