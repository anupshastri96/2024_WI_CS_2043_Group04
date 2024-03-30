import java.time.LocalDateTime;
import java.util.ArrayList;

public class clientTest {

    public static void main(String[] args) {
        Client client1 = new Client("Dominic", "Couillard", 1000, "512 NB Avenue", "dominic.couillard@hotmail.com",
                5064422924L, true,
                "Credit");
        System.out.println(client1.getName());
        System.out.println(client1.getClientId());
        System.out.println(client1.getEmailAddress());
        System.out.println(client1.getPhoneNumber());
        System.out.println(client1.getPaymentMethod());
        System.out.println(client1.toString());

        Appointment appointment1 = new Appointment(LocalDateTime.now(), "Simple Cleaning1", "Cleaning");
        Appointment appointment2 = new Appointment(LocalDateTime.now(), "Simple Cleaning2", "Cleaning");
        Appointment appointment3 = new Appointment(LocalDateTime.now(), "Simple Cleaning3", "Cleaning");
        Appointment appointment4 = new Appointment(LocalDateTime.now(), "Simple Cleaning4", "Cleaning");
        Appointment appointment5 = new Appointment(LocalDateTime.now(), "Simple Cleaning5", "Cleaning");
        Appointment appointment6 = new Appointment(LocalDateTime.now(), "Simple Cleaning6", "Cleaning");
        Appointment appointment7 = new Appointment(LocalDateTime.now(), "Simple Cleaning7", "Cleaning");
        Appointment appointment8 = new Appointment(LocalDateTime.now(), "Simple Cleaning8", "Cleaning");

        appointment1.markAsPaid();
        appointment3.markAsPaid();
        appointment4.markAsPaid();
        appointment7.markAsPaid();

        client1.addAppointment(appointment1);
        client1.addAppointment(appointment2);
        client1.addAppointment(appointment3);
        client1.addAppointment(appointment4);
        client1.addAppointment(appointment5);
        client1.addAppointment(appointment6);
        client1.addAppointment(appointment7);
        client1.addAppointment(appointment8);

        ArrayList<Appointment> pastAppointments = client1.pastAppointments();
        for (int i = 0; i < pastAppointments.size(); i++) {
            System.out.println(pastAppointments.get(i).toString());
        }

        System.out.println("\n");

        ArrayList<Appointment> upcomingAppointments = client1.upcomingAppointments();
        for (int i = 0; i < upcomingAppointments.size(); i++) {
            System.out.println(upcomingAppointments.get(i).toString());
        }
    }

}
