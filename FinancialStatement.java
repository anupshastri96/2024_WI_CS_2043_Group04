import java.io.FileWriter;

import java.util.ArrayList;

import java.io.IOException;

 

public class FinancialStatement {

    private ArrayList<Client> clients;

 

    public FinancialStatement(ArrayList<Client> clients) {

        this.clients = clients;

    }

 

    public void createStatement(String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("Financial Statement\n");

            double revenue = calculateRevenue();

            writer.write("Total Revenue: " + revenue + "\n");

            double profit = calculateProfit(revenue);

            writer.write("Total Profit: " + revenue + "\n");

            writer.write("Appointment Prices: \n");

            writePrice(writer);

            System.out.println("Success");

        } catch (IOException e) {

            System.out.println("There seems to be an error: " + e);

        }

 

    }

 

    public double calculateRevenue() {

        double revenue = 0;

        for (Client client : clients) {

            for (Appointment appointment : client.pastAppointments()) {

                if (appointment.isPaid() == true) {

                    if ((appointment.getType().equals("Cleaning") || appointment.getType().equals("Filling")

                            || appointment.getType().equals("Routine-Checkup")) == false) {

                        System.out.println(appointment.getType());

                        System.out.println("Invalid appointment type");

                    }

 

                    else {

                        revenue += appointment.getPrice();

                    }

                }

 

            }

        }

        return revenue;

    }

 

    public double calculateProfit(double revenue) {
       double expenses = 0;
       for (Client client : clients) {

            for (Appointment appointment : client.pastAppointments()) {

                if (appointment.isPaid()) {

                    if (appointment.getType().equals("Cleaning")) {

                        expenses =+ 120;

                        break;

                    } else if (appointment.getType().equals("Filling")) {

                        expenses += 250;

                        break;

                    } else if (appointment.getType().equals("Routine Check-Up")) {

                        expenses += 135;

                        break;

                    } else {

                        System.out.println("Invalid appointment type");

                        break;

                    }

                }

            }

        }
        return revenue - expenses;

          

    }

 

    public void writePrice(FileWriter writer) throws IOException {

        double cleaning = 0;

        double filling = 0;

        double checkup = 0;

 

        for (Client client : clients) {

            for (Appointment appointment : client.getAppointments()) {

                if (appointment.isPaid()) {

                    if (appointment.getType().equals("Cleaning")) {

                        cleaning = 120;

                        break;

                    } else if (appointment.getType().equals("Filling")) {

                        filling = 250;

                        break;

                    } else if (appointment.getType().equals("Routine Check-Up")) {

                        checkup = 135;

                        break;

                    } else {

                        System.out.println("Invalid appointment type");

                        break;

                    }

                }

            }

        }

        writer.write("Expenses per cleaning: $" + cleaning + "\n");

        writer.write("Expenses per filling: $" + filling + "\n");

        writer.write("Expenses per routine check-up: $" + checkup + "\n");

    }

}   
