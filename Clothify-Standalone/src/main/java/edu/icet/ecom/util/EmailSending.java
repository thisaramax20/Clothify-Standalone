package edu.icet.ecom.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailSending {
    public static void sendOTP(String sender, String otp){
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

    public static void sendInvoice(String sender, String invoiceFilePath){
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
            mimeMessage.setFrom(new InternetAddress(email));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sender));
            mimeMessage.setSubject("Your Invoice at Clothify (PVT) Ltd.");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Please find your invoice attached here within.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(new File(invoiceFilePath));

            multipart.addBodyPart(attachment);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
