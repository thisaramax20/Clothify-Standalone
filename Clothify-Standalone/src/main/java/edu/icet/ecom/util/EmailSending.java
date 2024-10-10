package edu.icet.ecom.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSending {
    public static void sendEmail(String sender,String otp){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String email = System.getenv("EMAIL");
        String password = System.getenv("PASSWORD");

        Session instance = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(instance);
        try {
            mimeMessage.setFrom(email);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sender));
            mimeMessage.setSubject("Your OTP Code");
            mimeMessage.setText("OTP is : "+otp);

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
