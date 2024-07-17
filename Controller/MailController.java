/*
 * Licensed under the default license template available at:
 * nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * To modify this template, visit:
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package Controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The MailController class handles sending OTP emails for password recovery.
 * This class uses the JavaMail API to send an email with a one-time password (OTP).
 * 
 * Usage:
 * MailController.sendOTP("recipient@example.com", "123456");
 * 
 * Note: Ensure that less secure app access is enabled for the Gmail account used.
 * 
 * @author TieuSoi
 */
public class MailController {
    
    private static final String USERNAME = "eyesluminous.bookstore@gmail.com";
    private static final String PASSWORD = "vwje gfwg xnmj ogjm";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_SUBJECT = "Yêu cầu thay đổi mật khẩu";


    /**
     * Sends an OTP to the specified email address.
     * 
     * @param emailTo The recipient's email address.
     * @param otp The one-time password to be sent.
     */
    public static void sendOTP(String emailTo, String otp) {
        Properties properties = configureMailProperties();

        Session session = createMailSession(properties);

        try {
            Message message = createEmailMessage(session, emailTo, otp);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures the properties for the mail session.
     * 
     * @return A Properties object with the configured mail properties.
     */
    private static Properties configureMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enables TLS
        return properties;
    }

    /**
     * Creates the mail session with the specified properties.
     * 
     * @param properties The mail session properties.
     * @return A Session object configured with the specified properties.
     */
    private static Session createMailSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    /**
     * Creates the email message with the specified session, recipient, and OTP.
     * 
     * @param session The mail session.
     * @param emailTo The recipient's email address.
     * @param otp The one-time password to be sent.
     * @return A Message object representing the email.
     * @throws MessagingException If there is an error creating the email message.
     */
    private static Message createEmailMessage(Session session, String emailTo, String otp) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        message.setSubject(EMAIL_SUBJECT);
        message.setContent(buildEmailBody(otp), "text/html; charset=utf-8");
        return message;
    }

    /**
     * Builds the email body for the OTP message with HTML formatting.
     * 
     * @param otp The one-time password.
     * @return The email body as an HTML string.
     */
    private static String buildEmailBody(String otp) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }"
                + ".container { max-width: 600px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background-color: #007BFF; color: #fff; padding: 10px 20px; border-top-left-radius: 8px; border-top-right-radius: 8px; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; font-size: 16px; line-height: 1.6; color: #333; }"
                + ".footer { padding: 10px 20px; background-color: #f4f4f4; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px; font-size: 14px; color: #555; text-align: center; }"
                + ".otp { display: inline-block; padding: 10px 20px; margin: 20px 0; font-size: 24px; font-weight: bold; color: #007BFF; border: 1px solid #007BFF; border-radius: 4px; background-color: #f9f9f9; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>"
                + "<h1>Yêu cầu thay đổi mật khẩu</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Xin chào,</p>"
                + "<p>Có ai đó đã yêu cầu đặt lại mật khẩu cho tài khoản của bạn. "
                + "Nếu đây không phải là bạn, vui lòng bỏ qua email này.</p>"
                + "<p>Sử dụng mã kích hoạt này để khôi phục mật khẩu của bạn:</p>"
                + "<div class='otp'>" + otp + "</div>"
                + "<p>Trân trọng,<br>Đội ngũ hỗ trợ</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>Lưu ý: Mã OTP này chỉ có hiệu lực trong vòng 10 phút.</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
