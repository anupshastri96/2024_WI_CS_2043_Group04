import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private LocalDateTime dateTime; // Date and time of the appointment
    private String description; // Description of the appointment
    private boolean canceled; // Flag indicating if the appointment is canceled
    private boolean paid; // Flag indicating if the appointment is paid
    private String type; // Type of appointment
    private double price; // Price of the appointment
    private final Duration cancellationTimeLimit = Duration.ofHours(24); // Time limit for cancellation

    // Constructor to initialize appointment details
    public Appointment(LocalDateTime dateTime, String description, String type) {
        this.dateTime = dateTime;
        this.description = description;
        this.canceled = false;
        this.paid = false;
        this.type = type;
        // Set the price based on the appointment type
        this.price = getPrice(type);
    }

    // Getter methods
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    // Setter methods

    public void setDateTime(LocalDateTime Date) {
        this.dateTime = Date;
    }

    // Method to set the type of appointment
    public void setType(String type) {
        this.type = type;
    }

    // Method to set the price of the appointment
    public void setPrice(double price) {
        this.price = price;
    }

    // Method to cancel the appointment
    public void cancel() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        // Calculate the duration until the appointment
        Duration timeUntilAppointment = Duration.between(now, dateTime);

        // Check if the time until the appointment is within the cancellation time limit
        // (24 hours)
        if (timeUntilAppointment.compareTo(cancellationTimeLimit) <= 0) {
            // If cancellation time limit is within the limit, apply deduction fee
            double deductionPercentage = 0.25; // 25% deduction fee
            // Calculate the deduction amount based on the appointment price
            double deductionAmount = deductionPercentage * price;
            // Print a message indicating the deduction amount applied for late cancellation
            System.out.println("A deduction of " + deductionAmount + " has been applied for late cancellation.");
        }

        // Mark the appointment as canceled
        this.canceled = true;
        // Print a message indicating the cancellation of the appointment
        System.out.println("Appointment canceled: " + description);
    }

    // Method to mark the appointment as paid
    public void markAsPaid() {
        this.paid = true;
        // Print a message indicating the payment status of the appointment
        System.out.println("Appointment marked as paid: " + description);
    }

    // Method to get the price based on appointment type
    public double getPrice(String appointmentType) {
        // Default price
        double price = 0.0;

        // Determine the price based on the appointment type
        switch (appointmentType) {
            case "Cleaning":
                price = 200.0;
                break;
            case "Routine-Checkup":
                price = 150.0;
                break;
            case "Filling":
                price = 250.0;
                break;
            // Add more cases for other appointment types with their corresponding prices
            default:
                System.out.println("Invalid appointment type. Please provide a valid appointment type.");
        }

        return price;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", canceled=" + canceled +
                ", paid=" + paid +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}