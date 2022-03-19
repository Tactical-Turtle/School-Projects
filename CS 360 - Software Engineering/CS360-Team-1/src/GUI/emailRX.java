package GUI;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

public class emailRX {

    public void emailRx(String fname, String patientEmail, String prescription, String instructions, String notes) {

        final String email = "testabc@gmail.com";
        final String password = "thisIsNotAnActualEmail";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(patientEmail)
            );
            message.setSubject("CS360 Prescription Details");
            String html = "<h4>Dear " + fname + ",</h4>" +
                    "The following are the details of your most recent prescription. "
                    + "This is an automated message, please call your doctor if you have any questions or concerns.<br>"
                    + "<p><b>Prescription:</b><br>"+ prescription +"</p>"
                    + "<p><b>Instructions:</b><br>"+ instructions +"</p>"
                    + "<p><b>Additional notes:</b><br>"+ notes +"</p><br>"
                    + "<p>To make an appointment, contact our office at:<br> "
                    + "Phone: (000) 000-0000 <br> Email: email@email.com <br> Location: 1234 street, city, ST 12345</p>";
            message.setContent(html, "text/html");

            Transport.send(message);
            System.out.println("Sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}