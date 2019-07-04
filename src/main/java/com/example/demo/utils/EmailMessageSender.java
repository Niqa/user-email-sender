package com.example.demo.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public abstract class EmailMessageSender {
    private static String USER_NAME = ""; //podaj meila
    private static String PASSWORD = ""; //podaj haslo


    public EmailMessageSender() {
    }

    public static void sendEmail(String to, String name){
        String from = USER_NAME;
        String pass = PASSWORD;
        String subject = EmailTemplates.SUBJECT;
        String message = EmailTemplates.WELCOME + " " + name + "!" + "\n\n" + EmailTemplates.MESSAGE;
        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            mimeMessage.addRecipient(Message.RecipientType.TO, toAddress);
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients()); //tu wysylam meila!
            transport.close();
        } catch (AddressException ae){
            ae.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }



}


