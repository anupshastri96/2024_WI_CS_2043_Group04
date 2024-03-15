import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Calendar;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class EmailTest {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail job = JobBuilder.newJob(EmailJob.class)
                .withIdentity("emailJob", "group1")
                .build();

        int year = 2024;
        int month = 3;
        int day = 13;
        int hour = 7;
        int minute = 46;
        int second = 0;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        Calendar scheduledTime = cal;

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(scheduledTime.getTime())
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

    }

    public static class EmailJob implements Job {
        public void execute(JobExecutionContext context) throws JobExecutionException {
            // Email sending logic
            sendEmail();
        }

        private void sendEmail() {

            // Current host is for hotmail.com
            String host = "smtp.office365.com";
            int port = 587;
            String user = "Enter your email";
            String pass = "Enter your password";

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
                String receiver = "dominic.couillard@hotmail.com";
                String sender = "dominic.couillard@hotmail.com";
                MimeMessage content = new MimeMessage(session);

                content.setFrom(new InternetAddress(sender));
                content.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
                content.setSubject("Email Test");
                content.setText("Test");

                Transport.send(content);
                System.out.println(
                        "This is a reminder that you have an appointment at ***** the ** of **. Please reply 'YES' to confirm the appointment");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}