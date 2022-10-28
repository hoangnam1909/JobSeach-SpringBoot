package com.nhn.util;

import com.nhn.entity.User;
import com.nhn.model.UserDTO;
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
                "        src=\"https://res.cloudinary.com/nhn1909/image/upload/v1666939819/iccnc1qrrt7rgmnonaqh.png\"\n" +
                "        height=\"60\"\n" +
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
                "      <div style=\"font-size: 24px\">Verify your account</div>\n" +
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

    public static EmailDetails welcomeMailForm(User user) {
        EmailDetails emailDetails = new EmailDetails();

        String subject = "Welcome to Job Search Spring Boot";

        String body = "<table\n" +
                "  align=\"center\"\n" +
                "  cellpadding=\"0\"\n" +
                "  cellspacing=\"0\"\n" +
                "  style=\"\n" +
                "    width: 100%;\n" +
                "    max-width: 600px;\n" +
                "    margin: 0 auto;\n" +
                "    border-style: solid;\n" +
                "    border-width: thin;\n" +
                "    border-color: #dadce0;\n" +
                "    border-radius: 8px;\n" +
                "  \"\n" +
                "  width=\"600\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td\n" +
                "        align=\"center\"\n" +
                "        style=\"\n" +
                "          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "          color: #111111;\n" +
                "          font-size: 14px;\n" +
                "          line-height: 18px;\n" +
                "          padding: 20px 15px 24px;\n" +
                "        \">\n" +
                "        <a\n" +
                "          href=\"#\"\n" +
                "          style=\"\n" +
                "            font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "            font-size: 14px;\n" +
                "            line-height: 18px;\n" +
                "            font-weight: bold;\n" +
                "            color: #005ce4;\n" +
                "            text-decoration: none;\n" +
                "          \"\n" +
                "          target=\"_blank\"\n" +
                "          data-saferedirecturl=\"#\">\n" +
                "          <img\n" +
                "            alt=\"Cloudinary Logo\"\n" +
                "            src=\"https://res.cloudinary.com/nhn1909/image/upload/v1666939819/iccnc1qrrt7rgmnonaqh.png\"\n" +
                "            width=\"150\"\n" +
                "            class=\"CToWUd\"\n" +
                "            data-bit=\"iit\" />\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td\n" +
                "        style=\"\n" +
                "          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "          color: #111111;\n" +
                "          font-size: 14px;\n" +
                "          line-height: 18px;\n" +
                "          border-radius: 0 0 10px 10px;\n" +
                "        \">\n" +
                "        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td\n" +
                "                bgcolor=\"#ffffff\"\n" +
                "                style=\"\n" +
                "                  font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "                  color: #111111;\n" +
                "                  font-size: 14px;\n" +
                "                  line-height: 18px;\n" +
                "                  border-radius: 6px;\n" +
                "                \">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        style=\"\n" +
                "                          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "                          color: #111111;\n" +
                "                          font-size: 14px;\n" +
                "                          line-height: 18px;\n" +
                "                          padding-left: 15px;\n" +
                "                          padding-right: 15px;\n" +
                "                          padding-top: 25px;\n" +
                "                          padding: 5px 40px 18px;\n" +
                "                        \">\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                align=\"center\"\n" +
                "                                style=\"\n" +
                "                                  font-family: Helvetica, Arial, Helvetica,\n" +
                "                                    sans-serif;\n" +
                "                                  color: #111111;\n" +
                "                                  font-size: 14px;\n" +
                "                                  line-height: 18px;\n" +
                "                                  padding: 0 0 8px;\n" +
                "                                  font: 500 24px/22px Arial, Helvetica,\n" +
                "                                    sans-serif, Fira;\n" +
                "                                  color: #0e2f5a;\n" +
                "                                \">\n" +
                "                                Welcome to Job Search Spring Boot\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                align=\"center\"\n" +
                "                                style=\"\n" +
                "                                  font-family: Helvetica, Arial, Helvetica,\n" +
                "                                    sans-serif;\n" +
                "                                  color: #111111;\n" +
                "                                  font-size: 14px;\n" +
                "                                  line-height: 18px;\n" +
                "                                  padding: 0 0 14px;\n" +
                "                                  font: 600 14px/18px Arial, Helvetica,\n" +
                "                                    sans-serif, Fira;\n" +
                "                                  color: #0066b8;\n" +
                "                                  border-bottom: 1px solid #c7cfd9;\n" +
                "                                \"></td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        align=\"center\"\n" +
                "                        style=\"\n" +
                "                          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "                          color: #111111;\n" +
                "                          font-size: 14px;\n" +
                "                          line-height: 18px;\n" +
                "                          padding-left: 0;\n" +
                "                          padding-right: 0;\n" +
                "                          padding: 20px 40px 0px;\n" +
                "                          font: 500 18px/24px Arial, Helvetica, sans-serif, Fira;\n" +
                "                        \">\n" +
                "                        <img\n" +
                "                          alt=\"Welcome to Cloudinary\"\n" +
                "                          src=\"[[avatar]]\"\n" +
                "                          width=\"300\"\n" +
                "                          class=\"CToWUd a6T\"\n" +
                "                          data-bit=\"iit\"\n" +
                "                          tabindex=\"0\" />\n" +
                "                        <div class=\"a6S\" dir=\"ltr\" style=\"opacity: 0.01\">\n" +
                "                          <div\n" +
                "                            id=\":2o\"\n" +
                "                            class=\"T-I J-J5-Ji aQv T-I-ax7 L3 a5q\"\n" +
                "                            title=\"Download\"\n" +
                "                            role=\"button\"\n" +
                "                            tabindex=\"0\"\n" +
                "                            aria-label=\"Download attachment \"\n" +
                "                            data-tooltip-class=\"a1V\">\n" +
                "                            <div class=\"akn\">\n" +
                "                              <div class=\"aSK J-J5-Ji aYr\"></div>\n" +
                "                            </div>\n" +
                "                          </div>\n" +
                "                        </div>\n" +
                "                        <br />\n" +
                "                        [[username]]\n" +
                "                        <br />\n" +
                "                        <br />\n" +
                "                        Congratulations! You're almost set to start using <br />\n" +
                "                        Job Search Spring Boot.\n" +
                "                        <br />\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        style=\"\n" +
                "                          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "                          color: #111111;\n" +
                "                          font-size: 14px;\n" +
                "                          line-height: 18px;\n" +
                "                          padding: 20px 40px;\n" +
                "                        \">\n" +
                "                        <h3\n" +
                "                          style=\"\n" +
                "                            font: 18px/20px Arial, Helvetica, sans-serif, Fira;\n" +
                "                            color: #0e2f5a;\n" +
                "                          \">\n" +
                "                          Account Details\n" +
                "                        </h3>\n" +
                "                        <table cellpadding=\"15\" cellspacing=\"0\" width=\"100%\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                bgcolor=\"#f1f5f8\"\n" +
                "                                style=\"\n" +
                "                                  font-family: Helvetica, Arial, Helvetica,\n" +
                "                                    sans-serif;\n" +
                "                                  color: #111111;\n" +
                "                                  font-size: 14px;\n" +
                "                                  line-height: 18px;\n" +
                "                                  padding-left: 30px;\n" +
                "                                  padding-right: 30px;\n" +
                "                                  padding: 23px 35px;\n" +
                "                                  border-radius: 10px;\n" +
                "                                \">\n" +
                "                                <table\n" +
                "                                  cellpadding=\"0\"\n" +
                "                                  cellspacing=\"0\"\n" +
                "                                  width=\"100%\">\n" +
                "                                  <tbody>\n" +
                "                                    <tr>\n" +
                "                                      <td\n" +
                "                                        style=\"\n" +
                "                                          font-family: Helvetica, Arial,\n" +
                "                                            Helvetica, sans-serif;\n" +
                "                                          color: #111111;\n" +
                "                                          font-size: 14px;\n" +
                "                                          line-height: 18px;\n" +
                "                                        \">\n" +
                "                                        <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                          <tbody>\n" +
                "                                            <tr>\n" +
                "                                              <td\n" +
                "                                                style=\"\n" +
                "                                                  font-family: Helvetica, Arial,\n" +
                "                                                    Helvetica, sans-serif;\n" +
                "                                                  color: #111111;\n" +
                "                                                  font-size: 14px;\n" +
                "                                                  line-height: 18px;\n" +
                "                                                  font: 18px/20px Arial,\n" +
                "                                                    Helvetica, sans-serif, Fira;\n" +
                "                                                  color: #0e2f5a;\n" +
                "                                                \">\n" +
                "                                                <table\n" +
                "                                                  cellpadding=\"5\"\n" +
                "                                                  cellspacing=\"2\"\n" +
                "                                                  width=\"100%\">\n" +
                "                                                  <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        <strong\n" +
                "                                                          >Username:</strong\n" +
                "                                                        >\n" +
                "                                                      </td>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        [[username]]\n" +
                "                                                      </td>\n" +
                "                                                    </tr>\n" +
                "                                                    <tr>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        <strong>Email:</strong>\n" +
                "                                                      </td>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        [[email]]\n" +
                "                                                      </td>\n" +
                "                                                    </tr>\n" +
                "                                                    <tr>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        <strong>Role:</strong>\n" +
                "                                                      </td>\n" +
                "                                                      <td\n" +
                "                                                        style=\"\n" +
                "                                                          font-family: Helvetica,\n" +
                "                                                            Arial, Helvetica,\n" +
                "                                                            sans-serif;\n" +
                "                                                          color: #111111;\n" +
                "                                                          font-size: 14px;\n" +
                "                                                          line-height: 18px;\n" +
                "                                                        \">\n" +
                "                                                        [[role]]\n" +
                "                                                      </td>\n" +
                "                                                    </tr>\n" +
                "                                                  </tbody>\n" +
                "                                                </table>\n" +
                "                                              </td>\n" +
                "                                            </tr>\n" +
                "                                          </tbody>\n" +
                "                                        </table>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                      <td\n" +
                "                                        style=\"\n" +
                "                                          font-family: Helvetica, Arial,\n" +
                "                                            Helvetica, sans-serif;\n" +
                "                                          color: #111111;\n" +
                "                                          font-size: 14px;\n" +
                "                                          line-height: 18px;\n" +
                "                                          font: 14px/20px Arial, Helvetica,\n" +
                "                                            sans-serif, Fira;\n" +
                "                                          color: #0e2f5a;\n" +
                "                                        \"></td>\n" +
                "                                    </tr>\n" +
                "                                  </tbody>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        style=\"\n" +
                "                          font-family: Helvetica, Arial, Helvetica, sans-serif;\n" +
                "                          color: #111111;\n" +
                "                          font-size: 14px;\n" +
                "                          line-height: 18px;\n" +
                "                          padding: 20px 40px 0;\n" +
                "                        \"></td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n";

        body = body.replace("[[avatar]]", user.getAvatar());
        body = body.replace("[[username]]", user.getUsername());
        body = body.replace("[[email]]", user.getEmail());
        body = body.replace("[[role]]", user.getRole());

        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(body);

        emailDetails.setRecipient(user.getEmail());
        return emailDetails;
    }

}
