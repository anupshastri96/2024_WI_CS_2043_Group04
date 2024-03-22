import java.io.*;
import java.util.ArrayList;

public class Client implements Serializable {
    private String givenName;
    private String lastName;
    private final int CLIENT_ID;
    private String emailAddress;
    private long phoneNumber;
    private Boolean insurance;
    private String paymentMethod;
    private ArrayList<Appointment> appointments;

    // Constructors
    public Client(String givenNameIn, String lastNameIn, int clientIdIn, String emailAddressIn, long phoneNumberIn,
            Boolean insuranceIn,
            String paymentMethodIn,
            ArrayList<Appointment> appointmentsIn) {
        givenName = givenNameIn;
        lastName = lastNameIn;
        CLIENT_ID = clientIdIn;
        emailAddress = emailAddressIn;
        phoneNumber = phoneNumberIn;
        insurance = insuranceIn;
        paymentMethod = paymentMethodIn;
        appointments = appointmentsIn;
    }

    public Client(String givenNameIn, String lastNameIn, int clientIdIn, String emailAddressIn, long phoneNumberIn,
            Boolean insuranceIn,
            String paymentMethodIn) {
        givenName = givenNameIn;
        lastName = lastNameIn;
        CLIENT_ID = clientIdIn;
        emailAddress = emailAddressIn;
        phoneNumber = phoneNumberIn;
        insurance = insuranceIn;
        paymentMethod = paymentMethodIn;
        appointments = new ArrayList<>(0);
    }

    // Getter methods
    public String getName() {
        return givenName + " " + lastName;
    }

    public int getClientId() {
        return CLIENT_ID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        String s = String.valueOf(phoneNumber);
        String areaCode = "(";
        int count1 = 0;
        String numFirstHalf = ") ";
        int count2 = 0;
        String numLastHalf = "-";
        for (int i = 0; i < s.length(); i++) {
            if (count1 < 3) {
                areaCode = areaCode + s.charAt(i);
                count1++;
            } else if (count2 < 3) {
                numFirstHalf = numFirstHalf + s.charAt(i);
                count2++;
            } else {
                numLastHalf = numLastHalf + s.charAt(i);
            }
        }

        return areaCode + numFirstHalf + numLastHalf;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<Appointment> pastAppointments() {
        ArrayList<Appointment> pastAppointments = new ArrayList<>(0);
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).isPaid() == true) {
                pastAppointments.add(appointments.get(i));
            }
        }

        return pastAppointments;
    }

    public ArrayList<Appointment> upcomingAppointments() {
        ArrayList<Appointment> upcomingAppointments = new ArrayList<>(0);
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).isPaid() == false) {
                upcomingAppointments.add(appointments.get(i));
            }
        }

        return upcomingAppointments;
    }

    // Mutators
    public void setAppointments(ArrayList<Appointment> appointmentsIn) {
        appointments = appointmentsIn;
    }

    public void changeGivenName(String newName) {
        givenName = newName;
    }

    public void changeLastName(String newLastName) {
        lastName = newLastName;
    }

    public void changeEmailAddress(String newEmail) {
        emailAddress = newEmail;
    }

    public void changePhoneNumber(long newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public void addAppointment(Appointment newAppointment) {
        appointments.add(newAppointment);
    }

    public void removeAppointment() {
        appointments.remove(appointments.size() - 1);
    }

    // toString()
    @Override
    public String toString() {
        return "Client[" +
                "givenName='" + givenName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientId=" + CLIENT_ID +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", insurance=" + insurance +
                ", paymentMethod='" + paymentMethod + '\'' +
                ']';
    }

}
