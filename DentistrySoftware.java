import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DentistrySoftware {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Client> clients = new ArrayList<>(); 
        Client client1 = new Client("Aidrien", "Smith", 1001, "aidriensmith@gmail.com", 1234567890L, true, "Credit");
        Client client2 = new Client("Mary", "Cooper", 1002, "mayrcooper@gmail.com", 1234567890L, true, "Credit");

        clients.add(client1);
        clients.add(client2);

        System.out.println("Welcome to the dental practice software, please enter a client ID:");
        int clientId = scanner.nextInt();

        Client currentClient = null;
        int index = -1;
        for (int i = 0; i < clients.size(); i++) {
            if (clientId == clients.get(i).getClientId()) {
            index = i;
            currentClient = clients.get(i);
            break;
            }
        }

        if (currentClient == null) {
            System.out.println("Client ID not found!");
            scanner.close();
            return;
        }
        Appointment newAppointment=null;
        System.out.println("Client Name: " + currentClient.getName());

        System.out.println("Select a command to perform:");
        System.out.println("Schedule an appointment(S)");
        System.out.println("Modify an appointment(M)");
        System.out.println("Cancel an appointment(C)");
        System.out.println("Print Client History(P)");
        System.out.println("Print Financial Statement(F)");
        System.out.println("Close(X)");

        System.out.print("->");
        String command = scanner.next();

        switch (command.toUpperCase()) {
            case "S":
                System.out.println("Enter appointment date and time (yyyy-MM-dd HH:mm):");
                String dateTimeString = scanner.next();
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
                System.out.println("Enter appointment description:");
                String description = scanner.next();
                System.out.println("Enter appointment type:");
                String type = scanner.next();
                System.out.println("Enter appointment price:");
                double price = scanner.nextDouble();
                newAppointment = new Appointment(dateTime, description, type, price);
                break;
            case "M":
                System.out.println("How would you like to modify the appointment:");
                System.out.println("Change Date(D)");
                System.out.println("Change Time(T)");
                System.out.println("Change Both(B)");
                System.out.println("Close(X)");
                System.out.print("->");
                String modify = scanner.next();
                switch (modify.toUpperCase()) {
                    case "D":
                        System.out.println("Please enter the modified date and time you would like to save (yyyy-MM-dd HH:mm):");
                        String newDateTimeString = scanner.next();
                        LocalDateTime newDateTime = LocalDateTime.parse(newDateTimeString);
                        newAppointment.setDateTime(newDateTime);
                        System.out.println("The appointment for client " + clientId + " has been changed to " + newDateTime + " for a " + newAppointment.getType());
                        break;
                    case "T":
                        System.out.println("Please enter the modified time you would like to save (HH:mm):");
                        String newTimeString = scanner.next();
                        LocalDateTime currentDateTime = newAppointment.getDateTime();
                        LocalDateTime newdateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalDateTime.parse(newTimeString).toLocalTime());
                        newAppointment.setDateTime(newdateTime);
                        System.out.println("The appointment for client " + clientId + " has been changed to " + newdateTime + " for a " + newAppointment.getType());
                        break;
                    case "B":
                        System.out.println("Please enter the modified date and time you would like to save (yyyy-MM-dd HH:mm):");
                        String newBothDateTimeString = scanner.next();
                        LocalDateTime newDatetime = LocalDateTime.parse(newBothDateTimeString);
                        newAppointment.setDateTime(newDatetime);
                        System.out.println("The appointment for client " + clientId + " has been changed to " + newDatetime + " for a " + newAppointment.getType());
                        break;
                    case "X":
                        System.out.println("Closing program...");
                        break;
                    default:
                        System.out.println("Invalid Command!");
                }
                break;
            case "C":
                if (newAppointment != null) {
                    newAppointment.cancel();
                } else {
                    System.out.println("No appointment to cancel.");
                }
                break;
            case "P":
                System.out.println("Client History");
                System.out.println("\nClient Name: " + currentClient.getName());
                System.out.println("Client ID: " + currentClient.getClientId());
                System.out.println("Contact Information: Email - " + currentClient.getEmailAddress() + ", Phone Number - " + currentClient.getPhoneNumber());
                System.out.println("Insurance Coverage: " + (currentClient.getInsurance() ? "Yes" : "No"));
                System.out.println("Preferred Payment Method: " + currentClient.getPaymentMethod());
                System.out.println("\nPast Appointments:");
                ArrayList<Appointment> pastAppointments = currentClient.pastAppointments();
                if (pastAppointments.isEmpty()) {
                    System.out.println("No past appointments found.");
                } else {
                    for (int i = 0; i < pastAppointments.size(); i++) {
                        Appointment appointment = pastAppointments.get(i);
                        System.out.println("Date & Time: " + appointment.getDateTime());
                        System.out.println("Description: " + appointment.getDescription());
                        System.out.println("Type: " + appointment.getType());
                        System.out.println("Price: " + appointment.getPrice());
                        System.out.println("-------------------------");
                    }
                }
                System.out.println("\nUpcoming Appointments:");
                ArrayList<Appointment> upcomingAppointments = currentClient.upcomingAppointments();
                if (upcomingAppointments.isEmpty()) {
                    System.out.println("No upcoming appointments found.");
                } else {
                    for (int i = 0; i < upcomingAppointments.size(); i++) {
                        Appointment appointment = upcomingAppointments.get(i);
                        System.out.println("Date & Time: " + appointment.getDateTime());
                        System.out.println("Description: " + appointment.getDescription());
                        System.out.println("Type: " + appointment.getType());
                        System.out.println("Price: " + appointment.getPrice());
                        System.out.println("-------------------------");
                    }
                }
                break;    
            case "F":
                System.out.println("Printing financial statement...");
                FinancialStatement financialStatement = new FinancialStatement(clients, newAppointment);
                financialStatement.createStatement("financial_statement.txt");
                break;
            case "X":
                System.out.println("Closing program...");
                break;
            default:
                System.out.println("Invalid command!");
        }
        scanner.close();
    }
}