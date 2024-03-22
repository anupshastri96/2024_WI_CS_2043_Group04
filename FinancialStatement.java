import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;


public class FinancialStatement {
    private ArrayList<Client> clients;
    private Appointment appointment;
     
    
   
   
   public FinancialStatement(ArrayList<Client> clients, Appointment appointment) {
       this.clients = clients;
       this.appointment = appointment;
   }
   
   public void createStatement(String fileName) {
      try(FileWriter writer = new FileWriter(fileName)) {
          writer.write("Financial Statement\n");
          double revenue = calculateRevenue();
          writer.write("Total Revenue: " + revenue + "\n");
          double profit = calculateProfit(revenue);
          writer.write("Total Profit: " + revenue + "\n");
          writer.write("Appointment Prices: \n");
          writePrice(writer);
          System.out.println("Success");
       }
       catch (IOException e){ 
          System.out.println("There seems to be an error: " + e);
       }
      
   }
    
    public double calculateRevenue() {
        double revenue = 0;
        for(Client client: clients) {
           for(Appointment appointment: client.getAppointments()) {
               if(appointment.isPaid() == true) {
                  if(appointment.getType() == "Cleaning") {
                     revenue += appointment.getPrice("Cleaning");
                  }
                  else if(appointment.getType() == "Filling") {
                     revenue += appointment.getPrice("Filling");
                  }
                  else if(appointment.getType() == "Routine Check-Up") {
                     revenue += appointment.getPrice("Routine Check-Up"
                     
                     );
                  }
                  else {
                     System.out.println("Invalid appointment type");
                  }
               }
                  
            }
         } 
         return revenue;     
    }
    
    public double calculateProfit(double revenue) {
       return revenue;
    }
    
     public void writePrice(FileWriter writer) throws IOException{
       double cleaning = 0;
       double filling = 0;
       double checkup = 0;
       
       for(Client client: clients) {
          for(Appointment appointment: client.getAppointments()) {
             if(appointment.isPaid()) {
                if (appointment.getType() == "Cleaning") {
                   cleaning = 120;
                   break;
                }
                else if(appointment.getType() == "Filling") {
                   filling = 250;
                   break;
                }
                else if(appointment.getType() == "Routine Check-Up") {
                   checkup = 135;
                   break;
                }
                else {
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
      
