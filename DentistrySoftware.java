import java.util.Scanner;
import org.quartz.JobExecutionException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime;
import java.io.*;

public class DentistrySoftware {
    public static void main(String[] args) throws JobExecutionException {

        ArrayList<Client> clients = new ArrayList<>();
        File file = new File("Clients.txt");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.txt"))) {
                clients = (ArrayList<Client>) in.readObject();
                for (int i = 0; i < clients.size(); i++) {
                    System.out.println(clients.get(i).getName() + ": "+ clients.get(i).getClientId());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Client client1 = new Client("Aidrien", "Smith", 1001, "512 UNB Avenue",
                    "aidriensmith@gmail.com",
                    1234567890L, true,
                    "Credit");
            Client client2 = new Client("Mary", "Cooper", 1002, "500 Saint John Ave",
                    "mayrcooper@gmail.com",
                    1234567890L, true, "Credit");

            clients.add(client1);
            clients.add(client2);
        }
        Scanner scanner = new Scanner(System.in);
        boolean close = false;
        while (close == false) {
            System.out.println(
                    "\nWelcome to the dental practice software, would you like to enter a new client (E), or interact with an existing client (I):");
            String choice = scanner.nextLine();

            Client currentClient = null;
            if (choice.equalsIgnoreCase("E")) {
                currentClient = addNewClient(clients);
            } else if (choice.equalsIgnoreCase("I")) {
                System.out.println("Enter a client ID: ");
                int clientId = scanner.nextInt();
                currentClient = null;
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
            } else {
                System.out.println("Invalid input!");
                scanner.close();
                return;
            }
            int clientId = currentClient.getClientId();
            Appointment newAppointment = null;
            System.out.println("Client Name: " + currentClient.getName());

            System.out.println("Select a command to perform:");
            System.out.println("Schedule an appointment(S)");
            System.out.println("Modify an appointment(M)");
            System.out.println("Cancel an appointment(C)");
            System.out.println("Complete appointment(A)");
            System.out.println("Print Client History(P)");
            System.out.println("Print Financial Statement(F)");
            System.out.println("Send Email Reminders (R)");
            System.out.println("Close(X)");

            System.out.print("->");
            String command = scanner.next();
            scanner.nextLine();

            switch (command.toUpperCase()) {
                case "S":
                    System.out.println("Enter appointment date and time (yyyy-MM-ddTHH:mm):");
                    String dateTimeString = scanner.next();
                    scanner.nextLine();
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
                    System.out.println("Enter appointment description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter appointment type:");
                    String type = scanner.next();
                    scanner.nextLine();
                    newAppointment = new Appointment(dateTime, description, type);

                    if (currentClient.getInsurance()) {
                        newAppointment.setPrice(newAppointment.getPrice() * 0.5);
                    }

                    currentClient.addAppointment(newAppointment);
                    System.out.println("A new appointment has been scheduled");
                    break;
                case "M":
                    System.out.println("Which appointment would you like to modify (Enter the appointment type):");
                    System.out.println(currentClient.upcomingAppointmentsToString());
                    String appType = scanner.nextLine();
                    Appointment currentAppointment = null;
                    for (int i = 0; i < currentClient.upcomingAppointments().size(); i++) {
                        if (currentClient.upcomingAppointments().get(i).getType().equals(appType)) {
                            currentAppointment = currentClient.upcomingAppointments().get(i);
                        }
                    }
                    System.out.println("How would you like to modify the appointment:");
                    System.out.println("Change Date(D)");
                    System.out.println("Change Time(T)");
                    System.out.println("Change Both(B)");
                    System.out.println("Close(X)");
                    System.out.print("->");
                    String modify = scanner.nextLine();
                    switch (modify.toUpperCase()) {
                        case "D":
                            System.out.println(
                                    "Please enter the modified date and time you would like to save (yyyy-MM-ddTHH:mm):");
                            String newDateTimeString = scanner.next();
                            scanner.nextLine();
                            LocalDateTime newDateTime = LocalDateTime.parse(newDateTimeString);
                            currentAppointment.setDateTime(newDateTime);
                            System.out.println("The appointment for client " + clientId + " has been changed to "
                                    + newDateTime + " for a " + currentAppointment.getType());
                            break;
                        case "T":
                            System.out.println("Please enter the modified time you would like to save (HH:mm):");
                            String newTimeString = scanner.nextLine();
                            LocalDateTime currentDateTime = currentAppointment.getDateTime();
                            LocalDateTime newdateTime = LocalDateTime.of(currentDateTime.toLocalDate(),
                                    LocalTime.parse(newTimeString));
                            currentAppointment.setDateTime(newdateTime);
                            System.out.println("The appointment for client " + clientId + " has been changed to "
                                    + newdateTime + " for a " + currentAppointment.getType());
                            break;
                        case "B":
                            System.out.println(
                                    "Please enter the modified date and time you would like to save (yyyy-MM-ddTHH:mm):");
                            String newBothDateTimeString = scanner.nextLine();
                            LocalDateTime newDatetime = LocalDateTime.parse(newBothDateTimeString);
                            currentAppointment.setDateTime(newDatetime);
                            System.out.println("The appointment for client " + clientId + " has been changed to "
                                    + newDatetime + " for a " + currentAppointment.getType());
                            break;
                        case "X":
                            System.out.println("Closing program...");
                            break;
                        default:
                            System.out.println("Invalid Command!");
                    }
                    break;
                case "C":
                    System.out.println("Which appointment would you like to cancel (Enter the appointment type):");
                    System.out.println(currentClient.upcomingAppointmentsToString());
                    String appType2 = scanner.nextLine();
                    Appointment currentAppointment2 = null;
                    for (int i = 0; i < currentClient.upcomingAppointments().size(); i++) {
                        if (currentClient.upcomingAppointments().get(i).getType().equals(appType2)) {
                            currentAppointment2 = currentClient.upcomingAppointments().get(i);
                        }
                    }

                    if (currentAppointment2 != null) {
                        currentAppointment2.cancel();
                    } else {
                        System.out.println("No appointment to cancel.");
                    }
                    break;
                case "A":
                    if (currentClient.upcomingAppointments().size() == 1) {
                        currentClient.upcomingAppointments().get(0).markAsPaid();
                        System.out.println("The appointment has been paid in full");
                        System.out.println(currentClient.pastAppointments().get(0).toString());
                    }
                    break;
                case "P":
                    System.out.println("Client History");
                    System.out.println("\nClient Name: " + currentClient.getName());
                    System.out.println("Client ID: " + currentClient.getClientId());
                    System.out.println("Contact Information: Email - " + currentClient.getEmailAddress()
                            + ", Phone Number - " + currentClient.getPhoneNumber());
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
                    FinancialStatement financialStatement = new FinancialStatement(clients);
                    financialStatement.createStatement("financial_statement.txt");
                    break;
                case "R":
                    for (Client client : clients) {
                        for (Appointment appointment : client.upcomingAppointments()) {
                            if (appointment == null) {

                            } else {
                                if (Duration.between(LocalDateTime.now(), appointment.getDateTime()).toHours() <= 24) {
                                    String email = client.getEmailAddress();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    String appointmentTime = appointment.getDateTime().format(formatter);
                                    String message = "This is a reminder that you have an appointment at the following date: "
                                            + appointmentTime + ". This is an appointment for a "
                                            + appointment.getType()
                                            + ".";
                                    SendEmail.execute(email, message);
                                }
                            }
                        }
                    }
                    System.out.println("Reminders have been sent!");
                    break;
                case "X":
                    System.out.println("Closing program...");
                    close = true;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
        scanner.close();

        try (final FileOutputStream fout = new FileOutputStream("Clients.txt");
                final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeObject(clients);
            out.flush();
            out.close();
            System.out.println("Success saved all clients");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Client addNewClient(ArrayList<Client> clients) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter client's given name: ");
        String givenName = scanner.nextLine();
        System.out.print("Enter client's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter client's address: ");
        String address = scanner.nextLine();
        System.out.print("Enter client's email address: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Enter client's phone number: ");
        long phoneNumber = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Does this person have insurance? (true/false): ");
        boolean insurance = scanner.nextBoolean();
        scanner.nextLine();
        System.out.println("What is this client's payment method? (Credit/Debit): ");
        String paymentMethod = scanner.nextLine();

        int newClientId = generateNewClientId(clients);

        Client newClient = new Client(givenName, lastName, newClientId, address, emailAddress, phoneNumber, insurance,
                paymentMethod,
                new ArrayList<>());

        clients.add(newClient);
        System.out.println();
        System.out.println("New client added successfully with ID: " + newClientId);
        return newClient;
    }

    private static int generateNewClientId(ArrayList<Client> clients) {
        int maxId = 999;
        for (Client client : clients) {
            if (client.getClientId() > maxId) {
                maxId = client.getClientId();
            }
        }
        return maxId + 1;
    }
}