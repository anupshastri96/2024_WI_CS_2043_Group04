import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.quartz.*;

public class SendEmail {

    public static void execute(String emailAddress, String message) throws JobExecutionException {
        // Email sending logic
        sendMail(emailAddress, message);
    }

    private static void sendMail(String emailAddress, String message) {

        // Current host is for hotmail.com
        String host = "smtp.office365.com";
        int port = 587;
        String user = "user email";
        String pass = "user password";

        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            String receiver = emailAddress;
            String sender = user;
            MimeMessage content = new MimeMessage(session);

            content.setFrom(new InternetAddress(sender));
            content.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            content.setSubject("Appointment Reminder");
            content.setText(message);

            Transport.send(content);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
