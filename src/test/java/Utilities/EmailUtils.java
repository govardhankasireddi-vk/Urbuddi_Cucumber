package Utilities;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


    public class EmailUtils {

        public static void sendReportByEmail() {
            final String username = "govardhan.kasireddi@optimworks.com";
           final String password = "fpjx ntty dkrg djqd"; // Use app-specific password if using Gmail

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kasireddigovardhan@gmail.com"));
                message.setSubject("TestNG Report - " + new Date());

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText("Please find the attached TestNG report.");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile("allure-report/index.html");
                multipart.addBodyPart(attachmentPart);

                message.setContent(multipart);
                Transport.send(message);

                System.out.println("Email sent successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

