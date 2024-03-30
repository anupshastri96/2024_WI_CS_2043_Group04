package MiniProjectTesting.doc;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class AppointmentTest {

    @Test
    public void testAppointmentCancellationWithinTimeLimit() {
        // Creating an appointment 20 hours in the future
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusHours(20);
        Appointment appointment = new Appointment(appointmentDateTime, "Test appointment", "scaling");

        // Cancel the appointment
        appointment.cancel();

        // Check if the appointment is canceled
        assertTrue(appointment.isCanceled());
    }

    @Test
    public void testAppointmentCancellationBeyondTimeLimit() {
        // Creating an appointment 30 hours in the future
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusHours(30);
        Appointment appointment = new Appointment(appointmentDateTime, "Test appointment", "scaling");

        // Cancel the appointment
        appointment.cancel();

        // Check if the appointment is canceled
        assertTrue(appointment.isCanceled());
    }

    @Test
    public void testAppointmentCancellationDeduction() {
        // Creating an appointment 20 hours in the future
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusHours(20);
        Appointment appointment = new Appointment(appointmentDateTime, "Test appointment", "Cleaning");

        // Cancel the appointment
        appointment.cancel();

        // Check if a deduction fee has been applied
        // For this test, we assume a deduction fee of 25% for late cancellation
        assertEquals(50.0, appointment.getPrice() * 0.25, 0.01); // 25% deduction from the original price (200.0)
    }

    @Test
    public void testGetPriceForValidType() {
        Appointment appointment = new Appointment(LocalDateTime.now(), "Test appointment", "Routine-Checkup");

        // Check if the price for a valid appointment type is correct
        assertEquals(150.0, appointment.getPrice(), 0.01);
    }

    @Test
    public void testGetPriceForInvalidType() {
        Appointment appointment = new Appointment(LocalDateTime.now(), "Test appointment", "invalid");

        // Check if the price for an invalid appointment type is 0.0
        assertEquals(0.0, appointment.getPrice(), 0.01);
    }

    @Test
    public void testMarkAsPaid() {
        Appointment appointment = new Appointment(LocalDateTime.now(), "Test appointment", "scaling");

        // Mark the appointment as paid
        appointment.markAsPaid();

        // Check if the appointment is marked as paid
        assertTrue(appointment.isPaid());
    }
}
